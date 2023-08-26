package de.maxbossing.mxcord.utils.manager

import de.maxbossing.mxcord.jda
import de.maxbossing.mxcord.modules.pastes.PastesCommand
import de.maxbossing.mxcord.utils.entities.SlashCommandEvent
import de.maxbossing.mxcord.utils.info
import dev.minn.jda.ktx.events.listener
import dev.minn.jda.ktx.interactions.commands.Command
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType

/**
 * Manages the Slash commands from the Bot
 */
object SlashCommandManager {
    private val commands = mapOf<String, SlashCommandEvent>(
        "pastes" to PastesCommand
    )

    fun startManager(jda: JDA) {
        jda.listener<SlashCommandInteractionEvent> {
            if (commands.containsKey(it.name)) {
                info("${it.user.asMention} executed `/${it.name}` in ${(it.channel as TextChannel).jumpUrl}")
                commands[it.name]!!.trigger(it)
            }
        }
    }

    init {
        jda.updateCommands()
            .addCommands(
                Command("pastes", "output a paste from pastes.dev in the channel")
                    .addOption(OptionType.STRING, "id", "the id of the paste")
            )
            .queue()
    }
}
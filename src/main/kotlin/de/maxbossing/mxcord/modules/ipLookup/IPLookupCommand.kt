package de.maxbossing.mxcord.modules.ipLookup

import de.maxbossing.mxcord.utils.entities.SlashCommandEvent
import dev.minn.jda.ktx.messages.Embed
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import java.net.InetAddress




object IPLookupCommand: SlashCommandEvent {
    override suspend fun trigger(it: SlashCommandInteractionEvent) {
        it.deferReply(true).queue()
        val machines = InetAddress.getAllByName(it.getOption("domain")!!.asString)
        val embed = Embed {
            title = "Non-authoritative Answer"
            description = "Name: ${it.getOption("domain")!!.asString}"
            field("Addressess", machines.joinToString("\n") { it.hostAddress }, false,)
        }
        it.hook.sendMessageEmbeds(embed).setEphemeral(true).queue()
    }
}
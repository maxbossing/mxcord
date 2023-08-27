package de.maxbossing.mxcord.modules.quote

import de.maxbossing.mxcord.utils.entities.SlashCommandEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

object QuoteCommand: SlashCommandEvent {
    override suspend fun trigger(it: SlashCommandInteractionEvent) {
        val name = it.getOption("name")!!.asString
        val file = this.javaClass.getResource("/quotes/${name}.txt")
        val quote = file!!.readText().split("\n").random()

        it.reply(quote).queue()
    }
}
package de.maxbossing.mxcord.modules.uptime

import de.maxbossing.mxcord.Main
import de.maxbossing.mxcord.utils.entities.SlashCommandEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

object UptimeCommand: SlashCommandEvent {
    override suspend fun trigger(it: SlashCommandInteractionEvent) {
        it.reply("<t:${Main.startupTime}:R> (<t:${Main.startupTime}:D>, <t:${Main.startupTime}:T>)").queue()
    }
}
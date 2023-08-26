package de.maxbossing.mxcord.utils.entities

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

/**
 * Represents an event that listens to a slash command
 */
interface SlashCommandEvent {
    suspend fun trigger(it: SlashCommandInteractionEvent)
}
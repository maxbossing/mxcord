package de.maxbossing.mxcord.modules.ping

import de.maxbossing.mxcord.jda
import de.maxbossing.mxcord.utils.entities.SlashCommandEvent
import dev.minn.jda.ktx.messages.Embed
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent


object PingCommand: SlashCommandEvent {
    override suspend fun trigger(it: SlashCommandInteractionEvent) {
        val gateWayPing = jda.gatewayPing
        jda.restPing.queue { time ->
            val embed = Embed {
                title = "\uD83C\uDFD3 Pong!"//🏓
                field("Gateway", "__${gateWayPing}__ms", true)
                field("Rest", "__${time}__ms", true)
            }
            it.replyEmbeds(embed).queue()
        }
    }
}
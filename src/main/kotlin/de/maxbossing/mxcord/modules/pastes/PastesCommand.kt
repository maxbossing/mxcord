package de.maxbossing.mxcord.modules.pastes

import de.maxbossing.mxcord.client
import de.maxbossing.mxcord.utils.entities.SlashCommandEvent
import de.maxbossing.mxcord.utils.info
import dev.minn.jda.ktx.messages.send
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

object PastesCommand: SlashCommandEvent {
    override suspend fun trigger(it: SlashCommandInteractionEvent) {
        it.deferReply().queue()

        val id = it.getOption("id")!!.asString

        val response = client.request("https://api.pastes.dev/$id") {
            method = HttpMethod.Get
        }

        val text = response.bodyAsText()
        val headers = response.headers

        if (response.status != HttpStatusCode.OK) {
            if (response.status == HttpStatusCode.NotFound) {
                it.hook.send("Paste not found!").setEphemeral(true).queue()
                return
            }
            it.hook.send("An unknow error occurred!").setEphemeral(true).queue()
            return
        }

        it.hook.send("```${headers["content-type"]!!.split("/")[1].split(";")[0]}\n$text\n```").setEphemeral(false).queue()

        info("Paste `$id` sent into ${(it.channel as TextChannel).jumpUrl} on request of ${it.user.asMention}")
    }
}
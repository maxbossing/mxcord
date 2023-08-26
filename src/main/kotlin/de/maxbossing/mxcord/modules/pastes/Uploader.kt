package de.maxbossing.mxcord.modules.pastes

import de.maxbossing.mxcord.client
import de.maxbossing.mxcord.jda
import dev.minn.jda.ktx.events.listener
import dev.minn.jda.ktx.messages.send
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

object Uploader {

    val messagelistener = jda.listener<MessageReceivedEvent> {

        if (it.author.isBot)
            return@listener


        val content = it.message.contentRaw

        if (!content.startsWith("```") || !content.endsWith("```"))
            return@listener

        var type = it.message.contentRaw.split("\n")[0].removePrefix("```")
        if (type == "") type = "plain"

        println(type)

        val pasteContent = content.removePrefix("```$type").removeSuffix("```")

        println(pasteContent)


        val response = client.request("https://api.pastes.dev/post") {
            method = HttpMethod.Post
            headers {
                append("content-type", "text/$type;charset=utf-8")
            }
            setBody(pasteContent)
        }

        val url = "https://pastes.dev/${response.headers["location"]}"

        it.message.delete().queue()
        it.channel.send("Your code was uploaded to $url to make it easier to read the channel!").queue()
    }
}
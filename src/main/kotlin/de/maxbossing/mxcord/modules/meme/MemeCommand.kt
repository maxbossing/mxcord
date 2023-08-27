package de.maxbossing.mxcord.modules.meme

import de.maxbossing.mxcord.client
import de.maxbossing.mxcord.utils.entities.SlashCommandEvent
import dev.minn.jda.ktx.messages.Embed
import dev.minn.jda.ktx.messages.send
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

object MemeCommand: SlashCommandEvent {
    override suspend fun trigger(it: SlashCommandInteractionEvent) {
        it.deferReply().queue()

        val response = client.request("https://meme-api.com/gimme") {
            method = HttpMethod.Get
        }

        val meme: Meme = response.body()

        val embed = Embed {
            title = meme.title
            image = meme.url
        }

        it.hook.sendMessageEmbeds(embed).queue()

    }
    @Serializable
    data class Meme(
        val postLink: String,
        val subreddit: String,
        val title: String,
        val url: String,
        val nsfw: Boolean,
        val spoiler: Boolean,
        val author : String,
        val ups: Int,
        val preview: List<String>
    )

/*
{
	"postLink": "https://redd.it/161nb0o",
	"subreddit": "memes",
	"title": "I thought Elon Musk was joking",
	"url": "https://i.redd.it/cd8nbrrffekb1.png",
	"nsfw": false,
	"spoiler": false,
	"author": "SirRoderic",
	"ups": 12990,
	"preview": [
		"https://preview.redd.it/cd8nbrrffekb1.png?width=108&crop=smart&auto=webp&s=183aa8b948b522d8e03a7fabcaa145de39a4ed87",
		"https://preview.redd.it/cd8nbrrffekb1.png?width=216&crop=smart&auto=webp&s=1db9f1ba7bbed49cf71a055055864c70da938167",
		"https://preview.redd.it/cd8nbrrffekb1.png?width=320&crop=smart&auto=webp&s=56afef686802aed4ea977ed0da4ba502635c20f6",
		"https://preview.redd.it/cd8nbrrffekb1.png?width=640&crop=smart&auto=webp&s=68810fd727e67246984a319206d27c822ce507f8",
		"https://preview.redd.it/cd8nbrrffekb1.png?width=960&crop=smart&auto=webp&s=fef1fb2495b3e2e4b84af9b5ac41da3970e17e83",
		"https://preview.redd.it/cd8nbrrffekb1.png?width=1080&crop=smart&auto=webp&s=b724af341d1d33140ca98c5524467569c4bc5ec4"
	]
}
 */

}
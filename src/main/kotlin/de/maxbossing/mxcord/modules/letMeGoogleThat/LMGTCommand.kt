package de.maxbossing.mxcord.modules.letMeGoogleThat

import de.maxbossing.mxcord.utils.entities.SlashCommandEvent
import de.maxbossing.mxcord.utils.info
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

object LMGTCommand: SlashCommandEvent {
    override suspend fun trigger(it: SlashCommandInteractionEvent) {
        val link = it.getOption("question")!!.asString.replace(" ", "+")

        val mention = if (it.getOption("ping") != null)
            it.getOption("ping")!!.asUser.asMention else ""

        it.reply("$mention\nhttps://letmegooglethat.com/?q=$link").queue()
        info("Sent letmegooglethat link in ${it.channel.asTextChannel().jumpUrl} with question ${it.getOption("question")}, pinging ${if (mention != "") mention else "nobody"}")
    }
}
package de.maxbossing.mxcord.modules.messageReactor

import de.maxbossing.mxcord.jda
import de.maxbossing.mxcord.utils.extensions.containsAny
import de.maxbossing.mxcord.utils.info
import dev.minn.jda.ktx.events.listener
import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

object MessageReactor {
    val listener = jda.listener<MessageReceivedEvent> {
        if (it.author.isBot)return@listener

        if (!it.message.contentRaw.lowercase().containsAny("cool", "kuhl", "kohl"))
            return@listener

        it.message.addReaction(Emoji.fromUnicode("U+1F60E")).queue()
        info("Reacted with `U+1F60E` to ${it.message.jumpUrl} from ${it.author.asMention}")

    }
}
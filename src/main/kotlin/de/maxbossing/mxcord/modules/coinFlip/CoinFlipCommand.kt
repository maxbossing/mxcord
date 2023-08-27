package de.maxbossing.mxcord.modules.coinFlip

import de.maxbossing.mxcord.utils.entities.SlashCommandEvent
import dev.minn.jda.ktx.messages.Embed
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.utils.FileUpload
import java.nio.file.OpenOption

object CoinFlipCommand: SlashCommandEvent {
    override suspend fun trigger(it: SlashCommandInteractionEvent) {
        val outcome = (0..1).random()
        val embed = Embed {
            title = if (outcome == 0) "Heads" else "Tails"
            image = if (outcome == 0)
                        "https://firebasestorage.googleapis.com/v0/b/static-a0c34.appspot.com/o/heads.png?alt=media&token=3115370e-c0c8-46ff-ac45-d76e902f7d8d"
                    else
                        "https://firebasestorage.googleapis.com/v0/b/static-a0c34.appspot.com/o/tails.png?alt=media&token=7e3c03e3-16f7-4631-a925-696933585441"
        }
        it.replyEmbeds(embed).queue()
    }
}
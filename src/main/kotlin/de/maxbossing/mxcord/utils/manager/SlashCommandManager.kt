@file:Suppress("InconsistentCommentForJavaParameter")

package de.maxbossing.mxcord.utils.manager

import de.maxbossing.mxcord.jda
import de.maxbossing.mxcord.modules.coinFlip.CoinFlipCommand
import de.maxbossing.mxcord.modules.dndDices.DnDDicesCommand
import de.maxbossing.mxcord.modules.ipLookup.IPLookupCommand
import de.maxbossing.mxcord.modules.letMeGoogleThat.LMGTCommand
import de.maxbossing.mxcord.modules.meme.MemeCommand
import de.maxbossing.mxcord.modules.pastes.PastesCommand
import de.maxbossing.mxcord.modules.ping.PingCommand
import de.maxbossing.mxcord.modules.quote.QuoteCommand
import de.maxbossing.mxcord.modules.uptime.UptimeCommand
import de.maxbossing.mxcord.utils.entities.SlashCommandEvent
import de.maxbossing.mxcord.utils.info
import dev.minn.jda.ktx.events.listener
import dev.minn.jda.ktx.interactions.commands.Command
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType

/**
 * Manages the Slash commands from the Bot
 */
object SlashCommandManager {
    private val commands = mapOf<String, SlashCommandEvent>(
        "pastes" to PastesCommand,
        "letmegooglethat" to LMGTCommand,
        "ping" to PingCommand,
        "iplookup" to IPLookupCommand,
        "meme" to MemeCommand,
        "uptime" to UptimeCommand,
        "quote" to QuoteCommand,
        "dndroll" to DnDDicesCommand,
        "coinflip" to CoinFlipCommand
    )

    fun startManager(jda: JDA) {
        jda.listener<SlashCommandInteractionEvent> {
            if (commands.containsKey(it.name)) {
                info("${it.user.asMention} executed `/${it.name}` in ${(it.channel as TextChannel).jumpUrl}")
                commands[it.name]!!.trigger(it)
            }
        }
    }

    init {
        jda.updateCommands()
            .addCommands(
                /*===System commands===*/

                //Ping command
                Command("ping", "Returns the bots Gateway and REST ping."),

                //Uptime Command
                Command("uptime", "Show how long the Bot is online"),

                /*===(apparently) Funny commands===*/

                //LMGTFY Command
                Command("letmegooglethat", "sends a lmgtfy link with the question in chat. Mention optional")
                    .addOption(OptionType.STRING, "question", "The question to google", true)
                    .addOption(OptionType.USER, "ping", "fill in the user which should be pinged with the link. Blank disables ping"),

                //Meme Generator
                Command("meme", "Get a random meme"),

                //Quote Generator
                Command("quote", "Get a random quote from a person")
                    .addOption(OptionType.STRING,"name", "The name of the person to get the quote from", true, true),

                //DnD Dice roll
                Command("dndroll", "Roll a DnD Dice")
                    .addOption(OptionType.STRING, "type", "Which dice to roll", true, true)
                    .addOption(OptionType.INTEGER, "amount", "How many times the dice should be rolled", true),

                //Coin Flip
                Command("coinflip", "Flip a Coin!"),
                /*===Utility Commands===*/

                //Access pastes.dev
                Command("pastes", "output a paste from pastes.dev in the channel")
                    .addOption(OptionType.STRING, "id", "the id of the paste", true),

                //Name Server lookup
                Command("iplookup", "Perform a non-authoritative nameserver lookup")
                    .addOption(OptionType.STRING, "domain", "The domain to perform the lookup on", true),
            )
            .queue()
    }
}
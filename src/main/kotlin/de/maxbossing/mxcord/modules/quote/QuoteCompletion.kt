package de.maxbossing.mxcord.modules.quote

import de.maxbossing.mxcord.jda
import dev.minn.jda.ktx.events.listener
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent

object QuoteCompletion {
    private val words = arrayOf("borat", "bart", "cartman", "homer", "stewie")

    val listener = jda.listener<CommandAutoCompleteInteractionEvent> {
        if (it.name == "quote" && it.focusedOption.name == "name") {
            it.replyChoiceStrings(words.filter { f ->
                f.startsWith(it.focusedOption.value)
            }).queue()
        }
    }
}
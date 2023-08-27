package de.maxbossing.mxcord.modules.dndDices

import de.maxbossing.mxcord.jda
import dev.minn.jda.ktx.events.listener
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent

object DnDDicesCompletion {
    private val words = arrayOf("d100", "d20", "d12", "d10", "d8", "d6", "d4", "percentile")

    val listener = jda.listener<CommandAutoCompleteInteractionEvent> {
        if (it.name == "dndroll" && it.focusedOption.name == "type") {
            it.replyChoiceStrings(words.filter { f ->
                f.startsWith(it.focusedOption.value)
            }).queue()
        }
    }
}
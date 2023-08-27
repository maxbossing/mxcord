package de.maxbossing.mxcord.modules.dndDices

import de.maxbossing.mxcord.utils.entities.SlashCommandEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

object DnDDicesCommand: SlashCommandEvent {
    override suspend fun trigger(it: SlashCommandInteractionEvent) {

        val rolls = mutableListOf<String>()
        for (roll in 1..it.getOption("amount")!!.asInt) {
            rolls += diceRoll(it.getOption("type")!!.asString)
        }
        it.reply(rolls.joinToString(", ")).queue()
    }

    private fun diceRoll(type: String): String {
        when (type) {
            "d100" -> {
                return (1..100).random().toString()
            }
            "d20" -> {
                return (1..20).random().toString()
            }
            "d12" -> {
                return (1..12).random().toString()
            }
            "d10" -> {
                return (1..10).random().toString()
            }
            "d8" -> {
                return (1..8).random().toString()
            }
            "d6" -> {
                return (1..6).random().toString()
            }
            "d4" -> {
                return (1..4).random().toString()
            }
            "percentile" -> {
                return "${(1..10).random()}0%"
            }
        }
        return "-1"
    }
}
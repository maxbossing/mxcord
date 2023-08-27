package de.maxbossing.mxcord.utils.manager

import de.maxbossing.mxcord.modules.dndDices.DnDDicesCompletion
import de.maxbossing.mxcord.modules.messageReactor.MessageReactor
import de.maxbossing.mxcord.modules.pastes.Uploader
import de.maxbossing.mxcord.modules.quote.QuoteCompletion

object ListenerManager {
    val listeners = listOf(
        Uploader,
        MessageReactor,
        QuoteCompletion,
        DnDDicesCompletion
    )
}
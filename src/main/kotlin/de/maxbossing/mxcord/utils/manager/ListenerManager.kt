package de.maxbossing.mxcord.utils.manager

import de.maxbossing.mxcord.modules.messageReactor.MessageReactor
import de.maxbossing.mxcord.modules.pastes.Uploader

object ListenerManager {
    val listeners = listOf(
        Uploader,
        MessageReactor
    )
}
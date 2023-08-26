package de.maxbossing.mxcord.utils

import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*
import com.github.ajalt.mordant.terminal.Terminal
import de.maxbossing.mxcord.Main
import de.maxbossing.mxcord.config.ConfigManager
import de.maxbossing.mxcord.jda
import dev.minn.jda.ktx.messages.send
import java.time.Instant


val terminal = Terminal()

fun info(msg: String, logDc: Boolean = true) {
    terminal.println("${(white + bold)("INFO: ")}${white(msg)}")
    if (logDc)dcLog("**INFO**: $msg")
}

fun warn(msg: String, logDc: Boolean = true) {
    terminal.println("${(yellow + bold)("WARNING: ")}${yellow(msg)}")
    if (logDc)dcLog("**WARNING**: $msg")
}

fun err(msg: String, logDc: Boolean = true) {
    terminal.println("${(red + bold)("ERROR: ")}${red(msg)}")
    if (logDc) dcLog("**ERROR**: $msg")
}

private fun dcLog(msg: String) {
    if (!Main.jdaInitialized)
        return
    val timestamp = Instant.now().epochSecond
    jda.getTextChannelById(ConfigManager.settings.logChannel)?.send("<t:$timestamp:T> $msg")?.queue()
}
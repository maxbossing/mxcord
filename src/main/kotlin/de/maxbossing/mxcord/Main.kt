package de.maxbossing.mxcord

import de.maxbossing.mxcord.config.ConfigManager
import de.maxbossing.mxcord.utils.err
import de.maxbossing.mxcord.utils.info
import de.maxbossing.mxcord.utils.manager.ListenerManager
import de.maxbossing.mxcord.utils.manager.SlashCommandManager
import dev.minn.jda.ktx.jdabuilder.default
import dev.minn.jda.ktx.jdabuilder.intents
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.requests.GatewayIntent
import java.io.File
import kotlin.system.exitProcess

fun main() {
    Main()
}

class Main() {
    companion object {
        lateinit var INSTANCE: Main
        lateinit var JDA: JDA

        lateinit var ktorClient: HttpClient

        var jdaInitialized = false
    }

    init {
        INSTANCE = this
        info("Instance Initialized")

        ConfigManager
        info("ConfigManager Initialized")

        JDA = default(ConfigManager.credentials.dcToken, enableCoroutines = true) {
            intents += GatewayIntent.MESSAGE_CONTENT
        }
        jdaInitialized = true
        info("JDA Instance Initialized")

        ktorClient = HttpClient(CIO)
        info("Ktor client Initialized")

        SlashCommandManager.startManager(jda)
        info("Slash Commands initialized")

        ListenerManager
        info("Listeners Initialized")

        consoleInputLoop()
    }

    private fun consoleInputLoop() {
        inputLoop@while (true) {
            val command = readln().split(" ")
            when (command[0]) {
                "config" -> {
                    if (command.size != 2) {
                        err("Usage: config <reload | save>", false)
                        continue@inputLoop
                    }
                    when (command[1]) {
                        "reload" -> {
                            ConfigManager.reload()
                        }
                        "save" -> {
                            ConfigManager.save()
                        }
                    }
                }
                "shutdown" -> {
                    info("Bot is offline")

                    client.close()

                    jda.shardManager?.setStatus(OnlineStatus.OFFLINE)
                    jda.shutdown()
                    jdaInitialized = false

                    exitProcess(0)
                }
                else -> {
                    err("Unknown command", false)
                    err("Possible commands:", false)
                    err(" - config <reload | save>", false)
                    err(" - shutdown")
                }

            }
        }
    }
}

val instance by lazy { Main.INSTANCE }
val jda by lazy { Main.JDA }
val client by lazy { Main.ktorClient }
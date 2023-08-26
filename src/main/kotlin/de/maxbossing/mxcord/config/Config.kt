@file:Suppress("unused")
package de.maxbossing.mxcord.config

import de.maxbossing.mxcord.utils.info
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object ConfigManager {
    var credentials: Credentials
    var settings: Settings

    private val configFolder = File("config")
    private val credentialsFile = File("config/credentials.json")
    private val settingsFile = File("config/settings.json")


    fun save() {
        credentialsFile.writeText(Json.encodeToString(credentials))
        settingsFile.writeText(Json.encodeToString(settings))
        info("Saved Config Files to disk")
    }
    fun reload() {
        credentials = Json.decodeFromString(credentialsFile.readText())
        settings = Json.decodeFromString(settingsFile.readText())
        info("Reloaded Config Files from disk")
    }


    init {
        // Create config folder
        if (!configFolder.exists() || !configFolder.isDirectory) configFolder.mkdirs()

        //create config files

        //check if they are present
        if (!credentialsFile.exists()) {
            credentialsFile.createNewFile()
            credentialsFile.writeText("{}")
        }

        if (!settingsFile.exists()){
            settingsFile.createNewFile()
            settingsFile.writeText("{}")
        }

        credentials = Json.decodeFromString(credentialsFile.readText())
        settings = Json.decodeFromString(settingsFile.readText())
    }
}

val credentials by lazy { ConfigManager.credentials }
val settings by lazy { ConfigManager.settings }


/**
 * Data class holding credentials
 */
@Serializable
data class Credentials(val dcToken: String)

/**
 * Data class holding settings
 */
@Serializable
data class Settings(val logChannel: Long)
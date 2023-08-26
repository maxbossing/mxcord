plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.serialization") version "1.9.0"
    application
}

group = "de.maxbossing"
version = "2"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    // JDA
    implementation("net.dv8tion", "JDA", "5.0.0-beta.13")
    implementation("com.github.minndevelopment", "jda-ktx","0.10.0-beta.1")

    // Kotlin plugins
    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.3.3")
    implementation("org.jetbrains.kotlinx","kotlinx-coroutines-core","1.7.3")

    // Terminal formatting
    implementation("com.github.ajalt.mordant", "mordant", "2.1.0")

    // Logging
    implementation("org.slf4j", "slf4j-api", "1.7.36")
    implementation("org.slf4j", "slf4j-simple", "1.7.36")

    //Ktor
    implementation("io.ktor","ktor-client-core","2.3.3")
    implementation("io.ktor","ktor-client-cio","2.3.3")
    implementation("io.ktor","ktor-client-content-negotiation","2.3.3")


}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}

kotlin {
    jvmToolchain(20)
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "de.maxbossing.mxcord.MainKt"
        }

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from(sourceSets.main.get().output)
        dependsOn(configurations.runtimeClasspath)
        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })
    }
}

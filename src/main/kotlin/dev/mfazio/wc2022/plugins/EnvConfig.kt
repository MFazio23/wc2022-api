package dev.mfazio.wc2022.plugins

import de.sharpmind.ktor.EnvConfig
import io.ktor.server.application.*

fun Application.configureEnvConfig() {
    EnvConfig.initConfig(environment.config)
}
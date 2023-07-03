package dev.mfazio.wwc2023

import dev.mfazio.wwc2023.plugins.configureEnvConfig
import dev.mfazio.wwc2023.plugins.configureHTTP
import dev.mfazio.wwc2023.plugins.configureMonitoring
import dev.mfazio.wwc2023.plugins.configureSecurity
import dev.mfazio.wwc2023.routing.configureRouting
import io.ktor.server.application.*
import io.ktor.server.tomcat.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureApp()
}

private fun Application.configureApp() {

    configureEnvConfig()

    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureRouting()
}
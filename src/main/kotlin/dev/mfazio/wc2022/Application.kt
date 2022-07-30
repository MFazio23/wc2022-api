package dev.mfazio.wc2022

import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import dev.mfazio.wc2022.plugins.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0") {
        configureSecurity()
        configureHTTP()
        configureMonitoring()
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureRouting()
}
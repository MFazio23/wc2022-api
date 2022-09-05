package dev.mfazio.wc2022

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dev.mfazio.wc2022.plugins.configureEnvConfig
import dev.mfazio.wc2022.plugins.configureHTTP
import dev.mfazio.wc2022.plugins.configureMonitoring
import dev.mfazio.wc2022.plugins.configureSecurity
import dev.mfazio.wc2022.routing.configureRouting
import dev.mfazio.wc2022.services.FirebaseAdmin
import io.ktor.server.application.*
import io.ktor.server.tomcat.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureApp()
}

private fun Application.configureApp() {

    configureEnvConfig()

    FirebaseAdmin.init()

    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureRouting()
}
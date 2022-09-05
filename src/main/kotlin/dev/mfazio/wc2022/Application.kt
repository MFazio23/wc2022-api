package dev.mfazio.wc2022

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dev.mfazio.wc2022.plugins.*
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
    configureDocumentation()

    FirebaseAdmin.init()

    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureRouting()
}
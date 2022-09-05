package dev.mfazio.wc2022.plugins

import io.ktor.server.auth.*
import io.ktor.server.application.*

fun Application.configureSecurity() {

    install(Authentication) {

    }

}

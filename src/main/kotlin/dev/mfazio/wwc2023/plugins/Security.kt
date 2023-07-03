package dev.mfazio.wwc2023.plugins

import dev.mfazio.wwc2023.auth.FirebaseAuthUser
import dev.mfazio.wwc2023.auth.firebase
import io.ktor.server.auth.*
import io.ktor.server.application.*

fun Application.configureSecurity() {

    install(Authentication) {
        firebase {
            validate { token ->
                FirebaseAuthUser(
                    userId = token.uid,
                    displayName = token.name,
                )
            }
        }
    }
}



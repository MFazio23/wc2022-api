package dev.mfazio.wc2022.plugins

import dev.mfazio.wc2022.auth.FirebaseAuthUser
import dev.mfazio.wc2022.auth.firebase
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



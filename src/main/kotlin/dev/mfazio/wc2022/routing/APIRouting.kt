package dev.mfazio.wc2022.routing

import dev.mfazio.wc2022.URLs
import dev.mfazio.wc2022.auth.FirebaseAuthName
import dev.mfazio.wc2022.auth.FirebaseAuthUser
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Application.configureRouting() {
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }

    install(Resources) { }

    routing {
        partyRouting()
        rankingsRouting()
        scheduleRouting()
        teamsRouting()

        get("/config") {
            //TODO: Remove this once we're going live.
            call.respond(
                mapOf(
                    "configApiUrl" to URLs.apiUrl,
                    "configDbUrl" to URLs.dbUrl,
                    "configWebUrl" to URLs.webUrl,
                    "configFirebaseAuthFileUrl" to URLs.firebaseAuthFileUrl,
                )
            )
        }

        route("auth-test") {
            get("open") {
                call.respondText { "Open" }
            }
            authenticate(FirebaseAuthName) {
                get("closed") {
                    call.respond(
                        APIResponse(
                            data = call.principal<FirebaseAuthUser>(),
                        )
                    )
                }
            }
        }
    }
}
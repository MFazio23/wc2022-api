package dev.mfazio.wc2022.routing

import dev.mfazio.wc2022.URLs
import dev.mfazio.wc2022.auth.FirebaseAuthName
import dev.mfazio.wc2022.auth.FirebaseAuthUser
import dev.mfazio.wc2022.repositories.RankingsRepository
import dev.mfazio.wc2022.repositories.ScheduleRepository
import dev.mfazio.wc2022.types.api.RankedTeamApiModel
import dev.mfazio.wc2022.types.api.ScheduledMatchApiModel
import dev.mfazio.wc2022.types.api.TeamApiModel
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

        route("auth-test") {
            get("open") {
                call.respondText { "Open" }
            }
            authenticate(FirebaseAuthName) {
                get("closed") {
                    call.respond(
                        APIResponse(
                            data = call.principal<FirebaseAuthUser>()
                        )
                    )
                }
            }
        }
    }
}
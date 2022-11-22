package dev.mfazio.wc2022.routing

import dev.mfazio.wc2022.URLs
import dev.mfazio.wc2022.auth.FirebaseAuthName
import dev.mfazio.wc2022.auth.FirebaseAuthUser
import dev.mfazio.wc2022.extensions.ok
import dev.mfazio.wc2022.repositories.PartyRepository
import dev.mfazio.wc2022.repositories.ScheduleRepository
import dev.mfazio.wc2022.types.api.StatusApiModel
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
        get("status") {
            call.ok(
                StatusApiModel(
                    partyCount =                    PartyRepository.getPartyCount(),
                    scheduledMatchCount = ScheduleRepository.getScheduledMatchCount()
                )
            )
        }
    }
}
package dev.mfazio.wwc2023.routing

import dev.mfazio.wwc2023.extensions.ok
import dev.mfazio.wwc2023.repositories.PartyRepository
import dev.mfazio.wwc2023.repositories.ScheduleRepository
import dev.mfazio.wwc2023.types.api.StatusApiModel
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.resources.*
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
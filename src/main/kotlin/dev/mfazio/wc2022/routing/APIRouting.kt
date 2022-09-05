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
        get("/") {
            call.respond(APIResponse<String>(message = "Hello world!"))
        }
        get("/config") {
            //TODO: Remove this once we're going live.
            call.respond(
                mapOf(
                    "configApiUrl" to URLs.apiUrl,
                    "configDbUrl" to URLs.dbUrl,
                    "configWebUrl" to URLs.webUrl,
                )
            )
        }
        route("/party") {
            get {
                //TODO: Get parties for user. User ID should be a query parameter
                call.respond(APIResponse.notYetImplemented)
            }
            post {
                //TODO: Create new party
                call.respond(APIResponse.notYetImplemented)
            }
            route("{token}") {
                post {
                    //TODO: Join party
                    val partyToken = call.parameters["token"]
                    call.respond(APIResponse.notYetImplemented)
                }
                put {
                    //TODO: Update party info (probably just name)
                    call.respond(APIResponse.notYetImplemented)
                }
                delete {
                    //TODO: Delete party
                    call.respond(APIResponse.notYetImplemented)
                }
                put("draft") {
                    //TODO: Draft for party
                    call.respond(APIResponse.notYetImplemented)
                }
                delete("{user-id}") {
                    //TODO: Remove user from party
                    call.respond(APIResponse.notYetImplemented)
                }
            }
        }
        route("/rankings") {
            get {
                call.respond(
                    APIResponse(
                        data = RankingsRepository.getTeamRankings().map(RankedTeamApiModel.Companion::fromRankedTeam)
                    )
                )
            }
            put {
                call.respond(
                    APIResponse(
                        data = RankingsRepository.updatedRankedTeams().map(RankedTeamApiModel.Companion::fromRankedTeam)
                    )
                )
            }
        }
        get("teams") {
            call.respond(APIResponse(data = TeamApiModel.allTeams()))
        }
        route("schedule") {
            get {
                call.respond(
                    APIResponse(
                        data = ScheduleRepository.getScheduledMatches()
                    )
                )
            }
            put {
                val schedule = ScheduleRepository.updateSchedule()
                call.respond(schedule.map(ScheduledMatchApiModel.Companion::fromScheduledMatch).sortedBy { it.dateTime })
            }
            route("{date}") {
                get {
                    //TODO: update to pull from DB
                    val date = LocalDate.parse(call.parameters["date"], DateTimeFormatter.ISO_LOCAL_DATE)
                    val scheduledMatches = ScheduleRepository.getExternalScheduleForDate(date)
                    call.respond(
                        APIResponse(
                            data = scheduledMatches
                        )
                    )
                }
                put {
                    //TODO: Update a single day
                    call.respond(
                        APIResponse.notYetImplemented
                    )
                }
            }

        }

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
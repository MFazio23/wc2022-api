@file:OptIn(KtorExperimentalLocationsAPI::class)

package dev.mfazio.wc2022.routing

import dev.mfazio.wc2022.URLs
import dev.mfazio.wc2022.repositories.RankingsRepository
import dev.mfazio.wc2022.routing.locations.TeamLocation
import dev.mfazio.wc2022.types.api.RankedTeamApiModel
import dev.mfazio.wc2022.types.api.TeamApiModel
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.plugins.contentnegotiation.*
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

    install(Locations) { }

    routing {
        get("/") {
            call.respond(APIResponse<String>(message = "Hello world!"))
        }
        get("/config") {
            call.respond(
                mapOf(
                    "configApiUrl" to URLs.apiUrl,
                    "configDbUrl" to URLs.dbUrl,
                    "configWebUrl" to URLs.webUrl,
                )
            )
        }
        route("/rankings") {
            get {
                call.respond(APIResponse.notYetImplemented)
            }
            put {
                RankingsRepository.updatedRankedTeams()
            }
        }
        get<TeamLocation> {
            call.respond(APIResponse(data = TeamApiModel.allTeams()))
        }
        get<TeamLocation.RankedTeamsLocation> {
            val rankedTeams = RankingsRepository.getExternalTeamRankings()
            println(rankedTeams)
            call.respond(rankedTeams.map(RankedTeamApiModel.Companion::fromRankedTeam).sortedBy { it.ranking })
        }
    }
}
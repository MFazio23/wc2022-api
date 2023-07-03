package dev.mfazio.wwc2023.routing

import dev.mfazio.wwc2023.repositories.RankingsRepository
import dev.mfazio.wwc2023.types.api.RankedTeamApiModel
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.rankingsRouting() {
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
}
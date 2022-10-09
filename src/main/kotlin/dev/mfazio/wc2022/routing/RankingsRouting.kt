package dev.mfazio.wc2022.routing

import dev.mfazio.wc2022.repositories.RankingsRepository
import dev.mfazio.wc2022.types.api.RankedTeamApiModel
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.rankingsRouting() {
    route("/rankings") {
        get {
            println(RankingsRepository.getTeamRankings().map(RankedTeamApiModel.Companion::fromRankedTeam))
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
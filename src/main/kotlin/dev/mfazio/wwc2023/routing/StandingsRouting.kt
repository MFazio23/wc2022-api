package dev.mfazio.wwc2023.routing

import dev.mfazio.wwc2023.repositories.StandingsRepository
import dev.mfazio.wwc2023.types.api.StandingsGroupApiModel
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.standingsRouting() {
    route("/standings") {
        get {
            call.respond(
                APIResponse(
                    data = StandingsRepository.getStandings()?.map(StandingsGroupApiModel.Companion::fromStandingsGroup)
                )
            )
        }
        put {
            call.respond(
                APIResponse(
                    data = StandingsRepository.updateStandings().map(StandingsGroupApiModel.Companion::fromStandingsGroup)
                )
            )
        }
    }
}
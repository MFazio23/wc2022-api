package dev.mfazio.wwc2023.routing

import dev.mfazio.wwc2023.extensions.badRequest
import dev.mfazio.wwc2023.extensions.noContent
import dev.mfazio.wwc2023.repositories.TeamRepository
import dev.mfazio.wwc2023.types.api.FrontEndTeamApiModel
import dev.mfazio.wwc2023.types.api.TeamApiModel
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.teamsRouting() {
    route("teams") {
        get {
            call.respond(APIResponse(data = TeamApiModel.allTeams()))
        }
        get("frontend") {
            call.respond(APIResponse(data = FrontEndTeamApiModel.allTeams()))
        }
        put("points") {
            if (TeamRepository.updateTeamPoints()) {
                call.noContent()
            } else {
                call.badRequest("Points not saved correctly.")
            }
        }
    }
}
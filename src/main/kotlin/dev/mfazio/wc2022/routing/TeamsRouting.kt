package dev.mfazio.wc2022.routing

import dev.mfazio.wc2022.types.api.FrontEndTeamApiModel
import dev.mfazio.wc2022.types.api.TeamApiModel
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
    }
}

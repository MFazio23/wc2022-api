package dev.mfazio.wc2022.routing

import dev.mfazio.wc2022.types.api.TeamApiModel
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.teamsRouting() {
    get("teams") {
        call.respond(APIResponse(data = TeamApiModel.allTeams()))
    }
}

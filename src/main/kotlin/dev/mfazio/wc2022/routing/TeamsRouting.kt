package dev.mfazio.wc2022.routing

import dev.mfazio.wc2022.types.api.TeamApiModel
import io.bkbn.kompendium.core.metadata.GetInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.teamsRouting() {
    getTeamsDocumentation()
    get("teams") {
        call.respond(APIResponse(data = TeamApiModel.allTeams()))
    }
}

private fun Route.getTeamsDocumentation() {
    install(NotarizedRoute()) {
        get = GetInfo.builder {
            summary("Get a list of teams")
            description("Lists all the teams taking part in this year's competition.")
            response {
                responseCode(HttpStatusCode.OK)
                responseType<List<TeamApiModel>>()
                description("Returns the list of teams")
            }
        }
    }
}
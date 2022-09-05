package dev.mfazio.wc2022.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.partyRouting() {
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
}
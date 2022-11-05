package dev.mfazio.wc2022.routing

import dev.mfazio.wc2022.auth.FirebaseAuthName
import dev.mfazio.wc2022.auth.FirebaseAuthUser
import dev.mfazio.wc2022.extensions.badRequest
import dev.mfazio.wc2022.extensions.notYetImplemented
import dev.mfazio.wc2022.extensions.ok
import dev.mfazio.wc2022.repositories.PartyRepository
import dev.mfazio.wc2022.types.api.PartyApiModel
import dev.mfazio.wc2022.types.api.TestPartyRequestApiModel
import dev.mfazio.wc2022.types.domain.Player
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Route.partyRouting() {
    route("/party") {
        get("/manual") {
            val userId = call.parameters["userId"]
            println("Manual call: $userId")

            val parties = PartyRepository.getPartiesForUser(userId)

            println(parties)

            call.respond(
                APIResponse(
                    data = parties.map { PartyApiModel.fromParty(it) }
                )
            )
        }

        authenticate(FirebaseAuthName) {
            get {
                val userPrincipal = call.principal<FirebaseAuthUser>()

                val parties = PartyRepository.getPartiesForUser(userPrincipal?.userId)

                call.respond(
                    APIResponse(
                        data = parties.map { PartyApiModel.fromParty(it)  }
                    )
                )
            }
            // Create new party
            post {
                val partyName = call.parameters["partyName"]
                val principal = call.principal<FirebaseAuthUser>()

                if (partyName == null || principal == null) {
                    call.badRequest()
                } else {
                    val party = PartyRepository.createParty(
                        name = partyName,
                        ownerId = principal.userId,
                        ownerName = principal.displayName
                    )

                    if (party != null) {
                        call.ok(PartyApiModel.fromParty(party))
                    } else call.partyNotFound()
                }
            }
            route("{code}") {
                // Join existing party
                post {
                    val partyCode = call.parameters["code"]
                    val principal = call.principal<FirebaseAuthUser>()

                    if (partyCode == null || principal == null) {
                        call.badRequest()
                    } else {
                        PartyRepository.addPlayerToParty(
                            partyCode = partyCode,
                            player = Player(
                                id = principal.userId,
                                name = principal.displayName,
                            )
                        )?.let { updatedParty ->
                            call.ok(PartyApiModel.fromParty(updatedParty))
                        } ?: call.partyNotFound()
                    }
                }
                // Update party name
                put {
                    val partyName = call.parameters["name"]
                    val partyCode = call.parameters["code"]
                    val principal = call.principal<FirebaseAuthUser>()

                    if (partyName == null || partyCode == null || principal == null) {
                        call.badRequest()
                    } else {
                        PartyRepository.updatePartyName(partyCode, partyName, principal.userId)?.let { updatedParty ->
                            call.ok(updatedParty)
                        } ?: call.badRequest()
                    }
                }
                delete {
                    val partyCode = call.parameters["code"]
                    val principal = call.principal<FirebaseAuthUser>()

                    if (partyCode == null || principal == null) {
                        call.badRequest()
                    } else {
                        PartyRepository.deleteParty(partyCode, principal.userId)
                    }
                }
                put("draft") {
                    //TODO: Draft for party
                    call.notYetImplemented()
                }
                delete("{user-id}") {
                    //TODO: Remove user from party
                    call.notYetImplemented()
                }
            }
        }
        post("test") {
            val playerCount = call.parameters["playerCount"]?.toIntOrNull()
            val ownerId = call.parameters["ownerId"]
            val ownerName = call.parameters["ownerName"]

            val partyRequest = call.receiveNullable<TestPartyRequestApiModel>()

            if (playerCount == null || ownerId == null || ownerName == null || partyRequest == null) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    APIResponse.withError("Parameters [teams] and [ownerId] are required, as is a PartyRequest body.")
                )
            } else {
                val party = PartyRepository.createTestParty(
                    name = partyRequest.name,
                    code = partyRequest.code,
                    ownerId = ownerId,
                    ownerName = ownerName,
                    playerCount = playerCount,
                    playersToInclude = partyRequest.playersToInclude.map { it.toPlayer() }
                )

                if (party == null) {
                    call.partyNotFound()
                } else {
                    call.respond(
                        APIResponse(
                            data = CreateTestPartyResponse(
                                ownerId,
                                ownerName,
                                playerCount,
                                partyRequest,
                                PartyApiModel.fromParty(party)
                            )
                        )
                    )
                }
            }
        }
    }
}

suspend fun ApplicationCall.partyNotFound() = this.badRequest("Party not found")

@Serializable
data class CreateTestPartyResponse(
    val ownerId: String?,
    val ownerName: String?,
    val playerCount: Int?,
    val testPartyRequestApiModel: TestPartyRequestApiModel,
    val party: PartyApiModel,
)
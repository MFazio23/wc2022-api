package dev.mfazio.wc2022.routing

import dev.mfazio.wc2022.auth.FirebaseAuthName
import dev.mfazio.wc2022.auth.FirebaseAuthUser
import dev.mfazio.wc2022.extensions.badRequest
import dev.mfazio.wc2022.extensions.noContent
import dev.mfazio.wc2022.extensions.ok
import dev.mfazio.wc2022.extensions.unauthorized
import dev.mfazio.wc2022.repositories.PartyRepository
import dev.mfazio.wc2022.types.api.PartyApiModel
import dev.mfazio.wc2022.types.api.RankingTypeApiModel
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

            val parties = PartyRepository.getPartiesForUser(userId)

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
                        data = parties.map { PartyApiModel.fromParty(it) }
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
                get {
                    val partyCode = call.parameters["code"] ?: return@get call.partyNotFound()

                    PartyRepository.getPartyByCode(partyCode)?.let { party ->
                        call.ok(PartyApiModel.fromParty(party))
                    } ?: call.partyNotFound()

                }
                // Join existing party
                post {
                    val partyCode = call.parameters["code"]
                    val principal = call.principal<FirebaseAuthUser>()

                    if (partyCode == null || principal == null) {
                        return@post call.badRequest()
                    }
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
                // Update party name
                put {
                    val partyName = call.parameters["name"]
                    val partyCode = call.parameters["code"]
                    val principal = call.principal<FirebaseAuthUser>()

                    if (partyName == null || partyCode == null || principal == null) {
                        return@put call.badRequest("Party not found")
                    }
                    PartyRepository.updatePartyName(partyCode, partyName, principal.userId)?.let { updatedParty ->
                        call.ok(PartyApiModel.fromParty(updatedParty))
                    } ?: call.badRequest()
                }
                delete {
                    val partyCode = call.parameters["code"]
                    val principal = call.principal<FirebaseAuthUser>()

                    if (partyCode == null || principal == null) {
                        return@delete call.badRequest()
                    }
                    val success = PartyRepository.deleteParty(partyCode, principal.userId)

                    if (success) {
                        call.noContent()
                    } else {
                        call.badRequest("Party deletion was unsuccessful.")
                    }
                }
                put("draft") {
                    val principal = call.principal<FirebaseAuthUser>()
                    val partyCode = call.parameters["code"]
                    val rankingType = call.parameters["rankingType"]?.let { RankingTypeApiModel.fromString(it) }?.toRankingType()
                    val teamsPerUser = call.parameters["teamsPerUser"]?.toIntOrNull()

                    if (principal == null || partyCode == null || rankingType == null || teamsPerUser == null) {
                        val errorMessage = when {
                            rankingType == null -> "Ranking type is required"
                            teamsPerUser == null -> "Teams per user is required"
                            else -> "Party not found"
                        }
                        return@put call.badRequest(errorMessage)
                    }

                    val party = PartyRepository.distributeTeamsForParty(principal.userId, partyCode, rankingType, teamsPerUser)

                    if (party != null) {
                        call.ok(PartyApiModel.fromParty(party))
                    } else {
                        call.badRequest()
                    }
                }
                delete("{userId}") {
                    val principal = call.principal<FirebaseAuthUser>()
                    val partyCode = call.parameters["code"]
                    val userId = call.parameters["userId"]

                    if (principal == null || partyCode == null || userId == null) {
                        return@delete call.partyNotFound()
                    }
                    val party = PartyRepository.getPartyByCode(partyCode) ?: return@delete call.partyNotFound()

                    if (party.owner.id != principal.userId && userId != principal.userId) {
                        return@delete call.unauthorized("You must either be removing yourself from the party or be the owner of the party.")
                    }

                    val updatedParty = PartyRepository.removePlayerFromParty(partyCode, userId)

                    if (updatedParty != null) {
                        call.ok(PartyApiModel.fromParty(updatedParty))
                    } else {
                        call.badRequest()
                    }
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
                    println("Null party")
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
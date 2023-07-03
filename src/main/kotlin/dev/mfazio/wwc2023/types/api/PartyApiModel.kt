package dev.mfazio.wwc2023.types.api

import dev.mfazio.wwc2023.types.domain.Party
import kotlinx.serialization.Serializable

@Serializable
data class PartyApiModel(
    val name: String,
    val code: String,
    val owner: String,
    val players: List<String> = emptyList(),
    val isDeleted: Boolean = false,
) {
    companion object {
        fun fromParty(party: Party) = PartyApiModel(
            name = party.name,
            code = party.code,
            owner = party.owner.name,
            players = party.playersWithTeams.mapNotNull { it?.player?.name },
            isDeleted = party.isDeleted
        )
    }
}

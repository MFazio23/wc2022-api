package dev.mfazio.wwc2023.types.api

import dev.mfazio.wwc2023.types.domain.RankedTeam
import kotlinx.serialization.Serializable

@Serializable
data class RankedTeamApiModel(
    val teamId: String,
    val fifa: Int?,
    val elo: Int?,
) {
    companion object {
        fun fromRankedTeam(rankedTeam: RankedTeam) = RankedTeamApiModel(
            teamId = TeamApiModel.fromTeam(rankedTeam.team).teamId,
            fifa = rankedTeam.fifaRanking?.ranking,
            elo = rankedTeam.eloRanking?.ranking,
        )
    }
}
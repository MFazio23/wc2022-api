package dev.mfazio.wc2022.types.api

import dev.mfazio.wc2022.types.domain.RankedTeam
import kotlinx.serialization.Serializable

@Serializable
data class RankedTeamApiModel(
    val teamId: String,
    val ranking: Int,
    val previousRanking: Int,
    val points: Double,
) {
    companion object {
        fun fromRankedTeam(rankedTeam: RankedTeam) = RankedTeamApiModel(
            teamId = TeamApiModel.fromTeam(rankedTeam.team).teamId,
            ranking = rankedTeam.ranking,
            previousRanking = rankedTeam.previousRanking,
            points = rankedTeam.points,
        )
    }
}
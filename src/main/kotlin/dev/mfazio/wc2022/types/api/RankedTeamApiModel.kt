package dev.mfazio.wc2022.types.api

import dev.mfazio.wc2022.types.RankedTeam
import kotlinx.serialization.Serializable

@Serializable
data class RankedTeamApiModel(
    val team: TeamApiModel,
    val ranking: Int,
    val previousRanking: Int,
    val points: Double,
) {
    companion object {
        fun fromRankedTeam(rankedTeam: RankedTeam) = RankedTeamApiModel(
            team = TeamApiModel.fromTeam(rankedTeam.team),
            ranking = rankedTeam.ranking,
            previousRanking = rankedTeam.previousRanking,
            points = rankedTeam.points,
        )
    }
}
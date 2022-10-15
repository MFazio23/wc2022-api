package dev.mfazio.wc2022.types.db

import dev.mfazio.wc2022.types.domain.RankedTeam
import kotlinx.serialization.Serializable

@Serializable
data class RankedTeamDbModel(
    val ranking: Int,
    val previousRanking: Int,
    val points: Double,
) {
    companion object {
        fun fromRankedTeam(rankedTeam: RankedTeam) = rankedTeam.team.teamId to RankedTeamDbModel(
            ranking = rankedTeam.ranking,
            previousRanking = rankedTeam.previousRanking,
            points = rankedTeam.points,
        )
    }
}

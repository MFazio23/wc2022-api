package dev.mfazio.wc2022.types.api

import dev.mfazio.wc2022.types.domain.RankedTeam
import dev.mfazio.wc2022.types.domain.RankingType
import kotlinx.serialization.Serializable

@Serializable
data class RankedTeamApiModel(
    val teamId: String,
    val fifa: Int,
    val elo: Int,
) {
    companion object {
        fun fromRankedTeam(rankedTeam: RankedTeam) = RankedTeamApiModel(
            teamId = TeamApiModel.fromTeam(rankedTeam.team).teamId,
            fifa = rankedTeam.fifaRanking.ranking,
            elo = rankedTeam.eloRanking.ranking,
        )
    }
}
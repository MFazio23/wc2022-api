package dev.mfazio.wc2022.types.db

import dev.mfazio.wc2022.types.domain.RankedTeam
import dev.mfazio.wc2022.types.domain.RankingType
import kotlinx.serialization.Serializable

@Serializable
data class RankedTeamDbModel(
    val name: String,
    val fifa: Int?,
    val elo: Int?,
) {
    companion object {
        fun fromRankedTeam(rankedTeam: RankedTeam) = rankedTeam.team.teamId to RankedTeamDbModel(
            name = rankedTeam.team.teamName,
            fifa = rankedTeam.fifaRanking?.ranking,
            elo = rankedTeam.eloRanking?.ranking,
        )
    }
}

package dev.mfazio.wc2022.mapping

import dev.mfazio.wc2022.types.RankedTeam
import dev.mfazio.wc2022.types.Team
import dev.mfazio.wc2022.types.external.ExternalTeamRankings

fun ExternalTeamRankings.mapToRankedTeams(): List<RankedTeam> {
    val externalTeams = this.rankings

    println(externalTeams)

    return Team.allTeams.mapNotNull { team ->
        val externalTeamRankingItem = externalTeams.find { it.rankingItem.countryCode == team.teamId }?.rankingItem

        if (externalTeamRankingItem != null) {
            RankedTeam(
                team = team,
                ranking = externalTeamRankingItem.rank,
                previousRanking = externalTeamRankingItem.previousRank,
                points = externalTeamRankingItem.totalPoints,
            )
        } else null
    }
}
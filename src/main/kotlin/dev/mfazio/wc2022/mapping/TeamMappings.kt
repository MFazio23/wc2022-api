package dev.mfazio.wc2022.mapping

import dev.mfazio.wc2022.types.RankedTeam
import dev.mfazio.wc2022.types.Team
import dev.mfazio.wc2022.types.db.RankedTeamDbModel
import dev.mfazio.wc2022.types.external.rankings.ExternalTeamRankings

fun ExternalTeamRankings.mapToRankedTeams(): List<RankedTeam> {
    val externalTeams = this.rankings

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
    }.sortedBy { it.ranking }
}

fun Map<String, RankedTeamDbModel>.mapToRankedTeams(): List<RankedTeam> = Team.allTeams.mapNotNull { team ->
    val dbRankingItem = this[team.teamId]

    if (dbRankingItem != null) {
        RankedTeam(
            team = team,
            ranking = dbRankingItem.ranking,
            previousRanking = dbRankingItem.previousRanking,
            points = dbRankingItem.points,
        )
    } else null
}.sortedBy { it.ranking }
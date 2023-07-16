package dev.mfazio.wwc2023.mapping

import dev.mfazio.wwc2023.types.domain.RankedTeam
import dev.mfazio.wwc2023.types.domain.Team
import dev.mfazio.wwc2023.types.db.RankedTeamDbModel
import dev.mfazio.wwc2023.types.domain.Ranking
import dev.mfazio.wwc2023.types.external.rankings.ExternalTeamRankings

fun ExternalTeamRankings.mapToRankedTeams(): List<RankedTeam> {
    val externalTeams = this.rankings

    return Team.allTeams.mapNotNull { team ->
        val externalTeamRankingItem = externalTeams.find { it.rankingItem.countryCode == team.teamId }?.rankingItem

        if (externalTeamRankingItem != null) {
            RankedTeam(
                team = team,
                fifaRanking = Ranking(
                    ranking = externalTeamRankingItem.rank,
                    previousRanking = externalTeamRankingItem.previousRank,
                    points = externalTeamRankingItem.totalPoints,
                )
            )
        } else null
    }.sortedBy { it.fifaRanking?.ranking }
}

fun Map<String, RankedTeamDbModel>.mapToRankedTeams(): List<RankedTeam> = Team.allTeams.mapNotNull { team ->
    val dbRankingItem = this[team.teamId]

    if (dbRankingItem != null) {
        RankedTeam(
            team = team,
            fifaRanking = Ranking(dbRankingItem.fifa),
        )
    } else null
}.sortedBy { it.fifaRanking?.ranking }
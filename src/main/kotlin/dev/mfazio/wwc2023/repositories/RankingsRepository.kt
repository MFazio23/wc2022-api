package dev.mfazio.wwc2023.repositories

import dev.mfazio.wwc2023.mapping.mapToRankedTeams
import dev.mfazio.wwc2023.services.RankingsService
import dev.mfazio.wwc2023.types.domain.RankedTeam
import dev.mfazio.wwc2023.types.domain.Ranking
import dev.mfazio.wwc2023.types.domain.Team

object RankingsRepository {
    suspend fun getExternalTeamRankings(): List<RankedTeam> {
        val fifaRankings = RankingsService.getFIFARankings()?.mapToRankedTeams() ?: emptyList()

        return mergeRankings(fifaRankings)
    }

    suspend fun getTeamRankings(): List<RankedTeam> {
        val firebaseRankings = RankingsService.getRankingsFromDB()

        return firebaseRankings?.mapToRankedTeams() ?: emptyList()
    }

    suspend fun updatedRankedTeams(): List<RankedTeam> = getExternalTeamRankings().also {
        RankingsService.saveRankingsToFirebase(it)
    }

    private fun mergeRankings(fifaRankings: List<RankedTeam>) = Team.allTeams.map { team ->
        val fifa = fifaRankings.find { it.team == team }?.fifaRanking

        RankedTeam(
            team = team,
            fifaRanking = Ranking(fifa?.ranking ?: -1),
        )
    }
}
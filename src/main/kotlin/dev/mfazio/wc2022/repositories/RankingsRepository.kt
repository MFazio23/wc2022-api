package dev.mfazio.wc2022.repositories

import dev.mfazio.utils.extensions.printEach
import dev.mfazio.wc2022.mapping.mapToRankedTeams
import dev.mfazio.wc2022.services.RankingsService
import dev.mfazio.wc2022.types.domain.RankedTeam
import dev.mfazio.wc2022.types.domain.Ranking
import dev.mfazio.wc2022.types.domain.Team
import dev.mfazio.wc2022.types.external.ELORanking

object RankingsRepository {
    suspend fun getExternalTeamRankings(): List<RankedTeam> {
        val fifaRankings = RankingsService.getFIFARankings()?.mapToRankedTeams() ?: emptyList()

        val eloRankings = getELORankingsFromTSV(RankingsService.getELORankings())

        return mergeRankings(fifaRankings, eloRankings)
    }

    suspend fun getTeamRankings(): List<RankedTeam> {
        val firebaseRankings = RankingsService.getRankingsFromDB()

        return firebaseRankings?.mapToRankedTeams() ?: emptyList()
    }

    suspend fun updatedRankedTeams(): List<RankedTeam> = getExternalTeamRankings().also {
        RankingsService.saveRankingsToFirebase(it)
    }

    private fun getELORankingsFromTSV(eloRankingsTSV: String?): List<ELORanking> = eloRankingsTSV?.lines()?.map { eloRankingsLine ->
        val (tournamentRankString, overallRankString, teamELOCode, ratingString) = eloRankingsLine.split("\t")

        ELORanking(
            tournamentRank = tournamentRankString.toInt(),
            overallRank = overallRankString.toInt(),
            teamELOCode = teamELOCode,
            rating = ratingString.toInt(),
        )
    } ?: emptyList()

    private fun mergeRankings(fifaRankings: List<RankedTeam>, eloRankings: List<ELORanking>) = Team.allTeams.map { team ->
        val fifa = fifaRankings.find { it.team == team }?.fifaRanking
        val elo = eloRankings.find { it.teamELOCode == team.eloCode }

        RankedTeam(
            team = team,
            fifaRanking = Ranking(fifa?.ranking ?: -1),
            eloRanking = Ranking(elo?.tournamentRank ?: -1)
        )
    }
}
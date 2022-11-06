package dev.mfazio.wc2022.repositories

import dev.mfazio.utils.extensions.printEach
import dev.mfazio.wc2022.mapping.mapToRankedTeams
import dev.mfazio.wc2022.services.RankingsService
import dev.mfazio.wc2022.types.domain.RankedTeam
import dev.mfazio.wc2022.types.external.ELORanking

object RankingsRepository {
    suspend fun getExternalTeamRankings(): List<RankedTeam> {
        val officialRankings = RankingsService.getOfficialRankings()?.mapToRankedTeams() ?: emptyList()

        val eloRankings = getELORankingsFromTSV(RankingsService.getELORankings())

        return addELORankings(officialRankings, eloRankings)
    }

    suspend fun getTeamRankings(): List<RankedTeam> {
        val firebaseRankings = RankingsService.getRankingsFromDB()

        return firebaseRankings?.mapToRankedTeams() ?: emptyList()
    }

    suspend fun updatedRankedTeams(): List<RankedTeam> = getExternalTeamRankings().also {
        RankingsService.saveRankingsToFirebase(it)
    }

    fun getELORankingsFromTSV(eloRankingsTSV: String?): List<ELORanking> = eloRankingsTSV?.lines()?.map { eloRankingsLine ->
        val (tournamentRankString, overallRankString, teamELOCode, ratingString) = eloRankingsLine.split("\t")

        ELORanking(
            tournamentRank = tournamentRankString.toInt(),
            overallRank = overallRankString.toInt(),
            teamELOCode = teamELOCode,
            rating = ratingString.toInt(),
        )
    } ?: emptyList()

    fun addELORankings(officialRankings: List<RankedTeam>, eloRankings: List<ELORanking>): List<RankedTeam> {

        return officialRankings.map { rankedTeam ->
            val eloRanking = eloRankings.find { it.teamELOCode == rankedTeam.team.flagCode }

            rankedTeam.copy(

            )
        }
    }
}

suspend fun main() {
    val eloRankingsTSV = RankingsService.getELORankings()

    val eloRankings = RankingsRepository.getELORankingsFromTSV(eloRankingsTSV)

    eloRankings.printEach()
}
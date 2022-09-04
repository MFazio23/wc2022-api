package dev.mfazio.wc2022.repositories

import dev.mfazio.wc2022.mapping.mapToRankedTeams
import dev.mfazio.wc2022.services.FirebaseService
import dev.mfazio.wc2022.services.RankingsService
import dev.mfazio.wc2022.types.RankedTeam

object RankingsRepository {
    suspend fun getExternalTeamRankings(): List<RankedTeam> =
        RankingsService.getExternalRankings()?.mapToRankedTeams() ?: emptyList()

    suspend fun getTeamRankings(): List<RankedTeam> {
        val firebaseRankings = RankingsService.getRankingsFromDB()

        return firebaseRankings?.mapToRankedTeams() ?: emptyList()
    }

    suspend fun updatedRankedTeams(): List<RankedTeam> = getExternalTeamRankings().also {
        FirebaseService.saveRankingsToFirebase(it)
    }
}
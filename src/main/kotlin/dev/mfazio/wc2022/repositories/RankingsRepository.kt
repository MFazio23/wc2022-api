package dev.mfazio.wc2022.repositories

import dev.mfazio.wc2022.mapping.mapToRankedTeams
import dev.mfazio.wc2022.services.FirebaseService
import dev.mfazio.wc2022.services.RankingsService
import dev.mfazio.wc2022.types.RankedTeam

object RankingsRepository {
    suspend fun getExternalTeamRankings(): List<RankedTeam> = RankingsService.getExternalRankings()?.mapToRankedTeams() ?: emptyList()

    suspend fun updatedRankedTeams(): List<RankedTeam> {

        return getExternalTeamRankings().also {
            FirebaseService.saveRankingsToFirebase(it)
        }
        /*val rankedTeams = getRankedTeams()

        FirebaseService.saveRankingsToFirebase(rankedTeams)

        return rankedTeams*/
    }
}
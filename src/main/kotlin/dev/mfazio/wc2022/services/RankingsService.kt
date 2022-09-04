package dev.mfazio.wc2022.services

import dev.mfazio.wc2022.URLs
import dev.mfazio.wc2022.types.db.RankedTeamDbModel
import dev.mfazio.wc2022.types.external.rankings.ExternalTeamRankings

object RankingsService : ApiService() {
    suspend fun getExternalRankings(): ExternalTeamRankings? = getResultOrNull(URLs.externalRankingsUrl)

    suspend fun getRankingsFromDB(): Map<String, RankedTeamDbModel>? = getResultOrNull(FirebaseService.getJsonUrlFromPath(URLs.firebaseRankingsUrl))
}
package dev.mfazio.wc2022.services

import dev.mfazio.wc2022.URLs
import dev.mfazio.wc2022.types.db.RankedTeamDbModel
import dev.mfazio.wc2022.types.domain.RankedTeam
import dev.mfazio.wc2022.types.external.rankings.ExternalTeamRankings
import java.time.LocalDateTime
import java.time.ZoneOffset

object RankingsService : ApiService() {
    suspend fun getRankingsFromDB(): Map<String, RankedTeamDbModel>? = getResultOrNull(FirebaseAdmin.getJsonUrlFromPath(URLs.firebaseRankingsUrl))

    suspend fun getFIFARankings(): ExternalTeamRankings? = getResultOrNull(URLs.fifaRankingsUrl)

    suspend fun getELORankings(): String? = getResultOrNull(getELORankingsUrl())

    private fun getELORankingsUrl() = URLs.eloRankingsUrl.replace("{timestamp}", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).toString())

    fun saveRankingsToFirebase(rankedTeams: List<RankedTeam>) {
        FirebaseAdmin.db
            .getReference("/rankings")
            .setValueAsync(rankedTeams.associate(RankedTeamDbModel.Companion::fromRankedTeam))
    }
}
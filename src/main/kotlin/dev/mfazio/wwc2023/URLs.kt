package dev.mfazio.wwc2023

import de.sharpmind.ktor.EnvConfig

object URLs {
    const val competitionId = 103
    const val seasonId = 285026
    const val stageId = 285033

    const val fifaRankingsUrl = "https://www.fifa.com/api/ranking-overview?locale=en&dateId=ranking_20230609"
    const val scheduleUrl =
        "https://api.fifa.com/api/v3/calendar/matches?from={startDateTime}&to={endDateTime}&language=en&count=4&idCompetition=$competitionId"
    const val matchDetailsUrl =
        "https://api.fifa.com/api/v3/live/football/$competitionId/255711/{stageId}/{matchId}?language=en"
    const val standingsUrl =
        "https://api.fifa.com/api/v3/calendar/$competitionId/$seasonId/$stageId/standing?language=en"

    const val firebaseRankingsUrl = "rankings"
    const val firebaseScheduleUrl = "schedule"

    val apiUrl = EnvConfig.getStringOrNull("apiUrl")
    val dbUrl = EnvConfig.getStringOrNull("dbUrl")
    val webUrl = EnvConfig.getStringOrNull("webUrl")
    val firebaseAuthFileUrl = EnvConfig.getStringOrNull("firebaseAuthFile")
}
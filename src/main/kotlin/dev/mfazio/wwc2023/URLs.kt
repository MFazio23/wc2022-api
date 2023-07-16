package dev.mfazio.wwc2023

import de.sharpmind.ktor.EnvConfig

object URLs {
    const val fifaRankingsUrl = "https://www.fifa.com/api/ranking-overview?locale=en&dateId=id13687"
    const val scheduleUrl =
        "https://api.fifa.com/api/v3/calendar/matches?from={startDateTime}&to={endDateTime}&language=en&count=4&idCompetition=17"
    const val matchDetailsUrl =
        "https://api.fifa.com/api/v3/live/football/17/255711/{stageId}/{matchId}?language=en"

    const val firebaseRankingsUrl = "rankings"
    const val firebaseScheduleUrl = "schedule"

    val apiUrl = EnvConfig.getStringOrNull("apiUrl")
    val dbUrl = EnvConfig.getStringOrNull("dbUrl")
    val webUrl = EnvConfig.getStringOrNull("webUrl")
    val firebaseAuthFileUrl = EnvConfig.getStringOrNull("firebaseAuthFile")
}
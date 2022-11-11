package dev.mfazio.wc2022

import de.sharpmind.ktor.EnvConfig

object URLs {
    const val fifaRankingsUrl = "https://www.fifa.com/api/ranking-overview?locale=en&dateId=id13687"
    const val eloRankingsUrl = "https://www.eloratings.net/2022_World_Cup.tsv?_={timestamp}"
    const val scheduleUrl =
        "https://api.fifa.com/api/v3/calendar/matches?from={startDateTime}&to={endDateTime}&language=en&count=500&idCompetition=17"
    const val matchDetailsUrl =
        "https://cxm-api.fifa.com/fifaplusweb/api/sections/matchdetails?locale=en&competitionId=17&seasonId=255711&stageId={stageId}&matchId={matchId}"

    const val firebaseRankingsUrl = "rankings"
    const val firebaseScheduleUrl = "schedule"

    val apiUrl = EnvConfig.getStringOrNull("apiUrl")
    val dbUrl = EnvConfig.getStringOrNull("dbUrl")
    val webUrl = EnvConfig.getStringOrNull("webUrl")
    val firebaseAuthFileUrl = EnvConfig.getStringOrNull("firebaseAuthFile")
}
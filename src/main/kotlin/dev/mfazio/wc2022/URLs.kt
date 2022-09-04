package dev.mfazio.wc2022

import de.sharpmind.ktor.EnvConfig

object URLs {
    const val prodAPIUrl = "https://wc2022-api.mfazio.dev"
    const val stagingAPIUrl = "https://wc2022-api-staging.mfazio.dev"

    const val prodDatabaseUrl = "https://wc2022.firebaseio.com/"
    const val stagingDatabaseUrl = "https://wc2022-staging.firebaseio.com/"

    const val prodWebUrl = "https://wc2022.mfazio.dev"

    const val externalRankingsUrl = "https://www.fifa.com/api/ranking-overview?locale=en&dateId=id13687"
    const val scheduleUrl =
        "https://cxm-api.fifa.com/fifaplusweb/api/sections/competitionpage/matches?competitionId=17&locale=en&date={date}&timezoneoffset=300"
    const val matchDetailsUrl =
        "https://cxm-api.fifa.com/fifaplusweb/api/sections/matchdetails?locale=en&competitionId=17&seasonId=255711&stageId={stageId}&matchId={matchId}"

    const val firebaseRankingsUrl = "rankings"
    const val firebaseScheduleUrl = "schedule"

    val apiUrl = EnvConfig.getStringOrNull("apiUrl")
    val dbUrl = EnvConfig.getStringOrNull("dbUrl")
    val webUrl = EnvConfig.getStringOrNull("webUrl")
}
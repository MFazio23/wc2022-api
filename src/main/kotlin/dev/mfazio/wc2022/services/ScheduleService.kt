package dev.mfazio.wc2022.services

import dev.mfazio.wc2022.URLs
import dev.mfazio.wc2022.types.ScheduledMatch
import dev.mfazio.wc2022.types.db.ScheduledMatchDbModel
import dev.mfazio.wc2022.types.external.matchdetails.ExternalMatchDetailsResponse
import dev.mfazio.wc2022.types.external.schedule.ExternalSchedule
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object ScheduleService : ApiService() {

    suspend fun getScheduleFromDb(): Map<String, ScheduledMatchDbModel>? = getResultOrNull(FirebaseService.getJsonUrlFromPath(URLs.firebaseScheduleUrl))

    suspend fun getExternalScheduleForDate(date: LocalDate): ExternalSchedule? = getResultOrNull(getExternalScheduleUrlForDate(date))

    fun saveScheduleToFirebase(scheduledMatches: List<ScheduledMatch>) {
        FirebaseService.db
            .getReference("/schedule")
            .setValueAsync(scheduledMatches.associate(ScheduledMatchDbModel.Companion::fromScheduledMatch))
    }

    private fun getExternalScheduleUrlForDate(date: LocalDate): String =
        URLs.scheduleUrl.replace("{date}", urlDateFormat.format(date))

    suspend fun getExternalMatchDetailsForMatch(matchId: String, stageId: String): ExternalMatchDetailsResponse? =
        getResultOrNull(getExternalMatchDetailsUrl(matchId, stageId))

    private fun getExternalMatchDetailsUrl(matchId: String, stageId: String): String =
        URLs.matchDetailsUrl.replace("{matchId}", matchId).replace("{stageId}", stageId)

    private val urlDateFormat = DateTimeFormatter.ISO_LOCAL_DATE
}
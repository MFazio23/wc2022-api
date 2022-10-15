package dev.mfazio.wc2022.services

import dev.mfazio.wc2022.URLs
import dev.mfazio.wc2022.extensions.atEndOfDayUtc
import dev.mfazio.wc2022.extensions.atStartOfDayUtc
import dev.mfazio.wc2022.types.domain.ScheduledMatch
import dev.mfazio.wc2022.types.db.ScheduledMatchDbModel
import dev.mfazio.wc2022.types.external.matchdetails.ExternalMatchDetailsResponse
import dev.mfazio.wc2022.types.external.schedule.ExternalSchedule
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object ScheduleService : ApiService() {

    suspend fun getScheduleFromDb(): Map<String, ScheduledMatchDbModel>? = getResultOrNull(FirebaseAdmin.getJsonUrlFromPath(URLs.firebaseScheduleUrl))

    suspend fun getExternalScheduleForDate(date: LocalDate): ExternalSchedule? = getResultOrNull(
        getExternalScheduleUrlForDate(date.atStartOfDayUtc(), date.atEndOfDayUtc())
    )

    fun saveScheduleToFirebase(scheduledMatches: List<ScheduledMatch>) {
        FirebaseAdmin.db
            .getReference("/schedule")
            .setValueAsync(scheduledMatches.associate(ScheduledMatchDbModel.Companion::fromScheduledMatch))
    }

    private fun getExternalScheduleUrlForDate(startDateTime: ZonedDateTime, endDateTime: ZonedDateTime): String =
        URLs.scheduleUrl
            .replace("{startDateTime}", urlDateTimeFormat.format(startDateTime))
            .replace("{endDateTime}", urlDateTimeFormat.format(endDateTime))

    suspend fun getExternalMatchDetailsForMatch(matchId: String, stageId: String): ExternalMatchDetailsResponse? =
        getResultOrNull(getExternalMatchDetailsUrl(matchId, stageId))

    private fun getExternalMatchDetailsUrl(matchId: String, stageId: String): String =
        URLs.matchDetailsUrl.replace("{matchId}", matchId).replace("{stageId}", stageId)

    private val urlDateTimeFormat = DateTimeFormatter.ISO_OFFSET_DATE_TIME
}
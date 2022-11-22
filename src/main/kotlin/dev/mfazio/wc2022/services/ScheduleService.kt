package dev.mfazio.wc2022.services

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dev.mfazio.wc2022.URLs
import dev.mfazio.wc2022.extensions.atEndOfDayUtc
import dev.mfazio.wc2022.extensions.atStartOfDayUtc
import dev.mfazio.wc2022.types.db.ScheduledMatchDbModel
import dev.mfazio.wc2022.types.domain.ScheduledMatch
import dev.mfazio.wc2022.types.external.matchdetails.ExternalMatchDetails
import dev.mfazio.wc2022.types.external.schedule.ExternalSchedule
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object ScheduleService : ApiService() {

    private const val baseSchedulePath = "/schedule"

    private val scheduleRef = FirebaseAdmin.db.getReference(baseSchedulePath)

    private var scheduledMatches: List<ScheduledMatchDbModel> = emptyList()

    init {
        scheduleRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot?) {
                snapshot?.children?.toList()?.let { dbMatches ->
                    scheduledMatches = dbMatches.map {
                        it.getValue(ScheduledMatchDbModel::class.java)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError?) {
                println("Schedule read failed: ${error?.code}")
            }
        })
    }

    fun getScheduledMatchCount() = scheduledMatches.size

    suspend fun getScheduleFromDb(): Map<String, ScheduledMatchDbModel>? = getResultOrNull(FirebaseAdmin.getJsonUrlFromPath(URLs.firebaseScheduleUrl))

    fun getScheduledMatchesForDate(localDate: LocalDate): List<ScheduledMatch> {
        println("Schedz: [$scheduledMatches]")
        return scheduledMatches.map { it.toScheduledMatch() }.filter { it.dateTime.toLocalDate() == localDate }
    }

    suspend fun getExternalScheduleForDate(date: LocalDate): ExternalSchedule? = getResultOrNull(
        getExternalScheduleUrlForDate(date.atStartOfDayUtc(), date.atEndOfDayUtc())
    )

    fun saveScheduleToFirebase(scheduledMatches: List<ScheduledMatch>) = try {
        FirebaseAdmin.db
            .getReference("/schedule")
            .setValueAsync(scheduledMatches.associate(ScheduledMatchDbModel.Companion::fromScheduledMatch))
            .get()

        true
    } catch (e: Exception) {
        false
    }

    fun saveScheduledMatchToFirebase(scheduledMatch: ScheduledMatch) = try {
        val (matchId, scheduledMatchDbModel) = ScheduledMatchDbModel.fromScheduledMatch(scheduledMatch)
        FirebaseAdmin.db
            .getReference("/schedule/$matchId")
            .setValueAsync(scheduledMatchDbModel)
            .get()

        true
    } catch (e: Exception) {
        false
    }

    private fun getExternalScheduleUrlForDate(startDateTime: ZonedDateTime, endDateTime: ZonedDateTime): String =
        URLs.scheduleUrl
            .replace("{startDateTime}", urlDateTimeFormat.format(startDateTime))
            .replace("{endDateTime}", urlDateTimeFormat.format(endDateTime))

    suspend fun getExternalMatchDetailsForMatch(matchId: String, stageId: String): ExternalMatchDetails? =
        getResultOrNull(getExternalMatchDetailsUrl(matchId, stageId))

    private fun getExternalMatchDetailsUrl(matchId: String, stageId: String): String =
        URLs.matchDetailsUrl.replace("{matchId}", matchId).replace("{stageId}", stageId)

    private val urlDateTimeFormat = DateTimeFormatter.ISO_OFFSET_DATE_TIME
}
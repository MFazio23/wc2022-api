package dev.mfazio.wc2022.repositories

import dev.mfazio.wc2022.mapping.mapToScheduledMatches
import dev.mfazio.wc2022.services.ScheduleService
import dev.mfazio.wc2022.types.domain.ScheduledMatch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object ScheduleRepository {
    private const val startDateString = "2022-11-20"
    private const val endDateString = "2022-12-20"

    private val startDate = LocalDate.parse(startDateString, DateTimeFormatter.ISO_LOCAL_DATE)
    private val endDate = LocalDate.parse(endDateString, DateTimeFormatter.ISO_LOCAL_DATE)

    fun getScheduledMatchCount() = ScheduleService.getScheduledMatchCount()

    suspend fun getScheduledMatches() = ScheduleService.getScheduleFromDb()?.mapToScheduledMatches() ?: emptyList()

    fun getScheduledMatchesForDate(localDate: LocalDate) = ScheduleService.getScheduledMatchesForDate(localDate)

    suspend fun getExternalSchedule(): List<ScheduledMatch> = startDate.datesUntil(endDate).toList().flatMap { date ->
        getExternalScheduleForDate(date) ?: emptyList()
    }

    suspend fun getExternalScheduleForDate(date: LocalDate) =
        ScheduleService.getExternalScheduleForDate(date)?.mapToScheduledMatches()

    suspend fun updateSchedule(): List<ScheduledMatch> = getExternalSchedule().also {
        ScheduleService.saveScheduleToFirebase(it)
    }

    suspend fun updateScheduleForDate(date: LocalDate): List<ScheduledMatch> {
        val matches = getExternalScheduleForDate(date) ?: emptyList()

        val saveResults = matches.map { ScheduleService.saveScheduledMatchToFirebase(it) }

        return if (saveResults.all { it }) matches else emptyList()
    }
}
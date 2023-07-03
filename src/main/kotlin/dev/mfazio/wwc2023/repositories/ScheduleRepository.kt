package dev.mfazio.wwc2023.repositories

import dev.mfazio.wwc2023.mapping.mapToScheduledMatches
import dev.mfazio.wwc2023.services.ScheduleService
import dev.mfazio.wwc2023.types.domain.MatchStatus
import dev.mfazio.wwc2023.types.domain.ScheduledMatch
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

    suspend fun getExternalScheduleForDate(date: LocalDate): List<ScheduledMatch>? = ScheduleService.getExternalScheduleForDate(date)?.mapToScheduledMatches()

    suspend fun updateSchedule(): List<ScheduledMatch> = getExternalSchedule().also {
        ScheduleService.saveScheduleToFirebase(it)
    }

    suspend fun updateScheduleForDate(date: LocalDate): List<ScheduledMatch> {
        val matches = getExternalScheduleForDate(date) ?: emptyList()

        val groupedMatches = matches.groupBy { it.matchStatus == MatchStatus.Live }

        val liveMatches = groupedMatches[true] ?: emptyList()
        val nonLiveMatches = groupedMatches[false] ?: emptyList()

        val saveResults = nonLiveMatches.map { ScheduleService.saveScheduledMatchToFirebase(it) }

        val liveResults = liveMatches.map { liveMatch ->
            if (liveMatch.stageId != null) {
                ScheduleService.getMatchDetails(liveMatch.matchId, liveMatch.stageId)?.let { liveMatchDetails ->
                    if (ScheduleService.saveScheduledMatchToFirebase(liveMatchDetails)) {
                        liveMatchDetails
                    } else null
                }
            } else null
        }

        val endResults = (liveResults.filterNotNull() + nonLiveMatches).distinctBy { it.matchId }

        return if (saveResults.all { it } && liveResults.all { it != null }) endResults else emptyList()
    }
}

data class TestVal(
    val name: String,
    val status: MatchStatus,
)
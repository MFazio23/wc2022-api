package dev.mfazio.wc2022.types.db

import dev.mfazio.wc2022.mapping.matchDateTimeFormat
import dev.mfazio.wc2022.types.domain.MatchStatus
import dev.mfazio.wc2022.types.domain.ScheduledMatch
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ScheduledMatchDbModel(
    val matchId: String? = null,
    val stageId: String? = null,
    val homeTeam: String? = null,
    val awayTeam: String? = null,
    val dateTime: String? = null,
    val group: String? = null,
    val location: String? = null,
    val stadium: String? = null,
    val matchStatus: String? = null,
    val homeScore: Int? = null,
    val awayScore: Int? = null,
    val matchTime: String? = null,
) {

    fun toScheduledMatch() = ScheduledMatch(
        matchId = matchId ?: "N/A",
        stageId = stageId,
        homeTeam = homeTeam ?: "N/A",
        awayTeam = awayTeam ?: "N/A",
        homeScore = homeScore,
        awayScore = awayScore,
        matchTime = matchTime,
        matchStatus = MatchStatus.valueOfOrUnknown(matchStatus),
        dateTime = LocalDateTime.parse(dateTime, matchDateTimeFormat),
        group = group,
        location = location,
        stadium = stadium,
    )

    companion object {
        fun fromScheduledMatch(scheduledMatch: ScheduledMatch) = scheduledMatch.matchId to ScheduledMatchDbModel(
            matchId = scheduledMatch.matchId,
            stageId = scheduledMatch.stageId,
            homeTeam = scheduledMatch.homeTeam,
            awayTeam = scheduledMatch.awayTeam,
            homeScore = scheduledMatch.homeScore,
            awayScore = scheduledMatch.awayScore,
            matchTime = scheduledMatch.matchTime,
            matchStatus = scheduledMatch.matchStatus.name,
            dateTime = scheduledMatch.dateTime.format(matchDateTimeFormat),
            group = scheduledMatch.group,
            location = scheduledMatch.location,
            stadium = scheduledMatch.stadium,
        )
    }
}

package dev.mfazio.wwc2023.types.db

import dev.mfazio.wwc2023.mapping.matchDateTimeFormat
import dev.mfazio.wwc2023.types.domain.MatchStatus
import dev.mfazio.wwc2023.types.domain.ScheduledMatch
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
    val homePenaltyScore: Int? = null,
    val awayPenaltyScore: Int? = null,
    val matchTime: String? = null,
    val winner: String? = null,
) {

    fun toScheduledMatch() = ScheduledMatch(
        matchId = matchId ?: "N/A",
        stageId = stageId,
        homeTeam = homeTeam ?: "N/A",
        awayTeam = awayTeam ?: "N/A",
        homeScore = homeScore,
        awayScore = awayScore,
        homePenaltyScore = homePenaltyScore,
        awayPenaltyScore = awayPenaltyScore,
        matchStatus = MatchStatus.valueOfOrUnknown(matchStatus),
        matchTime = matchTime,
        dateTime = LocalDateTime.parse(dateTime, matchDateTimeFormat),
        group = group,
        location = location,
        stadium = stadium,
        winner = winner,
    )

    companion object {
        fun fromScheduledMatch(scheduledMatch: ScheduledMatch) = scheduledMatch.matchId to ScheduledMatchDbModel(
            matchId = scheduledMatch.matchId,
            stageId = scheduledMatch.stageId,
            homeTeam = scheduledMatch.homeTeam,
            awayTeam = scheduledMatch.awayTeam,
            homeScore = scheduledMatch.homeScore,
            awayScore = scheduledMatch.awayScore,
            homePenaltyScore = scheduledMatch.homePenaltyScore,
            awayPenaltyScore = scheduledMatch.awayPenaltyScore,
            matchStatus = scheduledMatch.matchStatus.name,
            matchTime = scheduledMatch.matchTime,
            dateTime = scheduledMatch.dateTime.format(matchDateTimeFormat),
            group = scheduledMatch.group,
            location = scheduledMatch.location,
            stadium = scheduledMatch.stadium,
            winner = scheduledMatch.winner,
        )
    }
}

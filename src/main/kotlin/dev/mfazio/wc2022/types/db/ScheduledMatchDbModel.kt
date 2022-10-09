package dev.mfazio.wc2022.types.db

import dev.mfazio.wc2022.types.domain.ScheduledMatch
import kotlinx.serialization.Serializable

@Serializable
data class ScheduledMatchDbModel(
    val stageId: String,
    val homeTeam: String,
    val awayTeam: String,
    val dateTime: String,
    val group: String,
    val location: String,
    val stadium: String,
    val matchStatus: MatchStatusDbModel = MatchStatusDbModel.NotStarted,
    val homeScore: Int? = null,
    val awayScore: Int? = null,
    val currentMinute: Int? = null,
) {
    companion object {
        fun fromScheduledMatch(scheduledMatch: ScheduledMatch) = scheduledMatch.matchId to ScheduledMatchDbModel(
            stageId = scheduledMatch.stageId,
            homeTeam = scheduledMatch.homeTeam,
            awayTeam = scheduledMatch.awayTeam,
            dateTime = scheduledMatch.dateTime,
            group = scheduledMatch.group,
            location = scheduledMatch.location,
            stadium = scheduledMatch.stadium,
        )
    }
}

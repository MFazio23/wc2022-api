package dev.mfazio.wwc2023.types.api

import dev.mfazio.wwc2023.mapping.matchDateTimeFormat
import dev.mfazio.wwc2023.types.domain.ScheduledMatch
import kotlinx.serialization.Serializable

@Serializable
data class ScheduledMatchApiModel(
    val homeTeam: String,
    val awayTeam: String,
    val dateTime: String,
    val group: String?,
    val matchStatus: MatchStatusApiModel,
    val matchTime: String? = null,
    val homeScore: Int? = null,
    val awayScore: Int? = null,
    val homePenaltyScore: Int? = null,
    val awayPenaltyScore: Int? = null,
) {
    companion object {
        fun fromScheduledMatch(scheduledMatch: ScheduledMatch) = ScheduledMatchApiModel(
            homeTeam = scheduledMatch.homeTeam,
            awayTeam = scheduledMatch.awayTeam,
            dateTime = scheduledMatch.dateTime.format(matchDateTimeFormat),
            group = scheduledMatch.group,
            matchStatus = MatchStatusApiModel.fromMatchStatus(scheduledMatch.matchStatus),
            matchTime = scheduledMatch.matchTime,
            homeScore = scheduledMatch.homeScore,
            awayScore = scheduledMatch.awayScore,
            homePenaltyScore = scheduledMatch.homePenaltyScore,
            awayPenaltyScore = scheduledMatch.awayPenaltyScore,
        )
    }
}

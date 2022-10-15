package dev.mfazio.wc2022.types.domain

import kotlinx.serialization.Serializable

@Serializable
data class ScheduledMatch(
    val matchId: String,
    val stageId: String,
    val homeTeam: String,
    val awayTeam: String,
    val dateTime: String,
    val group: String,
    val location: String,
    val stadium: String,
    val matchStatus: MatchStatus = MatchStatus.NotStarted,
    val homeScore: Int? = null,
    val awayScore: Int? = null,
    val currentMinute: Int? = null,
)

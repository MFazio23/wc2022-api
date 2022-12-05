package dev.mfazio.wc2022.types.domain

import java.time.LocalDateTime

data class ScheduledMatch(
    val matchId: String,
    val stageId: String?,
    val homeTeam: String,
    val awayTeam: String,
    val dateTime: LocalDateTime,
    val group: String?,
    val location: String?,
    val stadium: String?,
    val matchStatus: MatchStatus = MatchStatus.ToBePlayed,
    val matchTime: String? = null,
    val homeScore: Int? = null,
    val awayScore: Int? = null,
    val homePenaltyScore: Int? = null,
    val awayPenaltyScore: Int? = null,
    val winner: String? = null,
) {
    val matchTitle = "$awayTeam vs. $homeTeam"
}
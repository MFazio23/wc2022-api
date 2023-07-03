package dev.mfazio.wwc2023.types.db

import dev.mfazio.wwc2023.types.domain.MatchStatus

enum class MatchStatusDbModel {
    Played,
    ToBePlayed,
    Live,
    Abandoned,
    Postponed,
    Cancelled,
    Forfeited,
    LineUps,
    Suspended,
    Unknown;

    fun toMatchStatus() = when(this) {
        Played -> MatchStatus.Played
        ToBePlayed -> MatchStatus.ToBePlayed
        Live -> MatchStatus.Live
        Abandoned -> MatchStatus.Abandoned
        Postponed -> MatchStatus.Postponed
        Cancelled -> MatchStatus.Cancelled
        Forfeited -> MatchStatus.Forfeited
        LineUps -> MatchStatus.LineUps
        Suspended -> MatchStatus.Suspended
        else -> MatchStatus.Unknown
    }

    companion object {
        fun fromMatchStatus(matchStatus: MatchStatus) = when(matchStatus) {
            MatchStatus.Played -> Played
            MatchStatus.ToBePlayed -> ToBePlayed
            MatchStatus.Live -> Live
            MatchStatus.Abandoned -> Abandoned
            MatchStatus.Postponed -> Postponed
            MatchStatus.Cancelled -> Cancelled
            MatchStatus.Forfeited -> Forfeited
            MatchStatus.LineUps -> LineUps
            MatchStatus.Suspended -> Suspended
            else -> Unknown
        }
    }
}

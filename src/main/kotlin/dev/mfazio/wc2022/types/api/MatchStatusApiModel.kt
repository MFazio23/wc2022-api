package dev.mfazio.wc2022.types.api

import dev.mfazio.wc2022.types.domain.MatchStatus
import kotlinx.serialization.Serializable

@Serializable
enum class MatchStatusApiModel {
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
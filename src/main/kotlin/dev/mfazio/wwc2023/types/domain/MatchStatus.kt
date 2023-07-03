package dev.mfazio.wwc2023.types.domain

enum class MatchStatus {
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
        fun valueOfOrUnknown(name: String? = null): MatchStatus = MatchStatus.values().find { it.name == name } ?: Unknown
    }
}
package dev.mfazio.wc2022.types.db

import dev.mfazio.wc2022.types.domain.MatchStatus

enum class MatchStatusDbModel {
    NotStarted,
    InProgress,
    Complete,
    Unknown;

    fun toMatchStatus() = when(this) {
        MatchStatusDbModel.NotStarted -> MatchStatus.NotStarted
        MatchStatusDbModel.InProgress -> MatchStatus.InProgress
        MatchStatusDbModel.Complete -> MatchStatus.Complete
        else -> MatchStatus.Unknown
    }
}

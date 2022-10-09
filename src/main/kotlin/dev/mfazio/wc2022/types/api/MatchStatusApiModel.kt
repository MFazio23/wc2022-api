package dev.mfazio.wc2022.types.api

import dev.mfazio.wc2022.types.domain.MatchStatus
import kotlinx.serialization.Serializable

@Serializable
enum class MatchStatusApiModel {
    NotStarted,
    InProgress,
    Complete,
    Unknown;

    companion object {
        fun fromMatchStatus(matchStatus: MatchStatus) = when(matchStatus) {
            MatchStatus.NotStarted -> NotStarted
            MatchStatus.InProgress -> InProgress
            MatchStatus.Complete -> Complete
            else -> Unknown
        }
    }
}
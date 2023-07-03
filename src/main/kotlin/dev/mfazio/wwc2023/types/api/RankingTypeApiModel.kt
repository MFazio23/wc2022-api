package dev.mfazio.wwc2023.types.api

import dev.mfazio.wwc2023.types.domain.RankingType
import kotlinx.serialization.Serializable

@Serializable
enum class RankingTypeApiModel {
    FIFA,
    ELO,
    Random;

    fun toRankingType(): RankingType = when (this) {
        FIFA -> RankingType.FIFA
        ELO -> RankingType.ELO
        Random -> RankingType.Random
    }

    companion object {
        fun fromString(stringRankingType: String?) =
            values().find { it.name.equals(stringRankingType, true) }
    }
}
package dev.mfazio.wc2022.types.api

import dev.mfazio.wc2022.types.domain.RankingType
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
}
package dev.mfazio.wc2022.types.domain

data class Ranking(
    val ranking: Int,
    val previousRanking: Int? = null,
    val points: Double? = null,
)

package dev.mfazio.wwc2023.types.domain

data class Ranking(
    val ranking: Int?,
    val previousRanking: Int? = null,
    val points: Double? = null,
)

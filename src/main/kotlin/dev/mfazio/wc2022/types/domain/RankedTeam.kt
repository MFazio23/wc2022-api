package dev.mfazio.wc2022.types.domain

data class RankedTeam(
    val team: Team,
    val ranking: Int,
    val previousRanking: Int,
    val points: Double,
)
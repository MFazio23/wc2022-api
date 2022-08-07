package dev.mfazio.wc2022.types

data class RankedTeam(
    val team: Team,
    val ranking: Int,
    val previousRanking: Int,
    val points: Double,
)
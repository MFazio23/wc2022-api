package dev.mfazio.wc2022.types.domain

data class RankedTeam(
    val team: Team,
    val fifaRanking: Ranking? = null,
    val eloRanking: Ranking? = null,
)
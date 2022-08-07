package dev.mfazio.wc2022.types

data class TeamWithPoints(
    val team: Team,
    val wins: Int = 0,
    val losses: Int = 0,
    val ties: Int = 0,
    val goalsFor: Int = 0,
    val goalsAgainst: Int = 0,
    val cleanSheets: Int = 0,
)

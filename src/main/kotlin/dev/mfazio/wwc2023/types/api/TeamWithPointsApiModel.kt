package dev.mfazio.wwc2023.types.api

data class TeamWithPointsApiModel(
    val team: String,
    val wins: Int = 0,
    val losses: Int = 0,
    val ties: Int = 0,
    val goalsFor: Int = 0,
    val goalsAgainst: Int = 0,
    val cleanSheets: Int = 0,
    val isEliminated: Boolean = false,
)
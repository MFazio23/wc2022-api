package dev.mfazio.wwc2023.types.db

import dev.mfazio.wwc2023.types.domain.TeamWithPoints

data class TeamWithPointsDbModel(
    val wins: Int = 0,
    val losses: Int = 0,
    val ties: Int = 0,
    val goalsFor: Int = 0,
    val goalsAgainst: Int = 0,
    val goalPoints: Int = 0,
    val cleanSheets: Int = 0,
    val isEliminated: Boolean = false,
) {
    companion object {
        fun fromTeamWithPoints(teamWithPoints: TeamWithPoints) = TeamWithPointsDbModel(
            wins = teamWithPoints.wins,
            losses = teamWithPoints.losses,
            ties = teamWithPoints.ties,
            goalsFor = teamWithPoints.goalsFor,
            goalsAgainst = teamWithPoints.goalsAgainst,
            goalPoints = teamWithPoints.goalPoints,
            cleanSheets = teamWithPoints.cleanSheets,
            isEliminated = teamWithPoints.isEliminated,
        )
    }
}
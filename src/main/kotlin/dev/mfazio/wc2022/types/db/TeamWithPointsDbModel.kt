package dev.mfazio.wc2022.types.db

import dev.mfazio.wc2022.types.domain.TeamWithPoints

data class TeamWithPointsDbModel(
    val wins: Int = 0,
    val losses: Int = 0,
    val ties: Int = 0,
    val goalsFor: Int = 0,
    val goalsAgainst: Int = 0,
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
            cleanSheets = teamWithPoints.cleanSheets,
            isEliminated = teamWithPoints.isEliminated,
        )
    }
}
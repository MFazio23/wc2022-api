package dev.mfazio.wwc2023.mapping

import dev.mfazio.wwc2023.types.domain.Team
import dev.mfazio.wwc2023.types.domain.TeamWithPoints
import dev.mfazio.wwc2023.types.external.standings.ExternalStanding

//fun ExternalStandings.mapToStandingsGroup() = this.results.

fun ExternalStanding.toTeamWithPoints() = Team.getTeamById(this.team.abbreviation)?.let { team ->
    TeamWithPoints(
        team = team,
        wins = this.won,
        losses = this.lost,
        ties = this.drawn,
        goalsFor = this.forX,
        goalsAgainst = this.against,
        goalPoints = this.goalsDifference,
    )
}
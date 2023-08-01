package dev.mfazio.wwc2023.repositories

import dev.mfazio.wwc2023.services.ScheduleService
import dev.mfazio.wwc2023.services.TeamService
import dev.mfazio.wwc2023.types.domain.ScheduledMatch
import dev.mfazio.wwc2023.types.domain.Team
import dev.mfazio.wwc2023.types.domain.TeamWithPoints

object TeamRepository {
    fun getTeamsWithPoints(completedMatches: List<ScheduledMatch>? = null): Map<String, TeamWithPoints> {
        val matches = completedMatches ?: ScheduleService.getCompletedMatches()

        val matchPoints = matches.flatMap { match ->
            listOf(
                Team.getTeamById(match.homeTeam)?.let { team ->
                    TeamWithPoints(
                        team = team,
                        wins = if (match.winner == team.teamId) 1 else 0,
                        losses = if (match.winner == team.teamId) 0 else 1,
                        ties = if (match.homeScore == match.awayScore) 1 else 0,
                        goalsFor = match.homeScore ?: 0,
                        goalsAgainst = match.awayScore ?: 0,
                        goalPoints = minOf((match.homeScore ?: 0), 5),
                        cleanSheets = if (match.awayScore == 0) 1 else 0,
                    )
                },
                Team.getTeamById(match.awayTeam)?.let { team ->
                    TeamWithPoints(
                        team = team,
                        wins = if (match.winner == team.teamId) 1 else 0,
                        losses = if (match.winner == team.teamId) 0 else 1,
                        ties = if (match.homeScore == match.awayScore) 1 else 0,
                        goalsFor = match.awayScore ?: 0,
                        goalsAgainst = match.homeScore ?: 0,
                        goalPoints = minOf((match.awayScore ?: 0), 5),
                        cleanSheets = if (match.homeScore == 0) 1 else 0,
                    )
                },
            )
        }.filterNotNull()

        return Team.allTeams.associate { team ->
            team.teamId to matchPoints.filter { it.team == team}.fold(TeamWithPoints(team)) { total, points ->
                TeamWithPoints(
                    team = team,
                    wins = total.wins + points.wins,
                    losses = total.losses + points.losses,
                    ties = total.ties + points.ties,
                    goalsFor = total.goalsFor + points.goalsFor,
                    goalsAgainst = total.goalsAgainst + points.goalsAgainst,
                    goalPoints = total.goalPoints + points.goalPoints,
                    cleanSheets = total.cleanSheets + points.cleanSheets,
                    isEliminated = TeamService.isTeamEliminated(team)
                )
            }
        }
    }
    fun updateTeamPoints(): Boolean {
        val teamsWithPoints = getTeamsWithPoints()

        return TeamService.saveTeamPoints(teamsWithPoints)
    }
}
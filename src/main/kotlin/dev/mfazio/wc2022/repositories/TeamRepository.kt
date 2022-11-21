package dev.mfazio.wc2022.repositories

import dev.mfazio.wc2022.services.TeamService
import dev.mfazio.wc2022.types.domain.Team
import dev.mfazio.wc2022.types.domain.TeamWithPoints
import kotlin.random.Random

object TeamRepository {
    fun updateTeamPoints(): Boolean {
        //TODO: actually get the points.
        val teamsWithPoints = Team.allTeams.map {
            TeamWithPoints(
                it,
                /*wins = Random.nextInt(0, 5),
                losses = Random.nextInt(0, 5),
                ties = Random.nextInt(0, 4),
                goalsFor = Random.nextInt(0, 13),
                goalsAgainst = Random.nextInt(0, 13),
                cleanSheets = Random.nextInt(0, 3),
                isEliminated = Random.nextBoolean(),*/
            )
        }

        return TeamService.saveTeamPoints(teamsWithPoints)
    }
}
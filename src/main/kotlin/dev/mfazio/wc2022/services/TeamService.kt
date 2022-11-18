package dev.mfazio.wc2022.services

import dev.mfazio.wc2022.types.db.TeamWithPointsDbModel
import dev.mfazio.wc2022.types.domain.TeamWithPoints

object TeamService {
    fun saveTeamPoints(teamsWithPoints: List<TeamWithPoints>): Boolean {
        val dbTeamsWithPoints = teamsWithPoints.associate { it.team.teamId to TeamWithPointsDbModel.fromTeamWithPoints(it) }

        return try {
            FirebaseAdmin.db
                .getReference("points")
                .setValueAsync(dbTeamsWithPoints)
                .get()

            true
        } catch (e: Exception) {
            false
        }
    }
}
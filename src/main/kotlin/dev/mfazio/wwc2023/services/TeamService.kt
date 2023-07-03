package dev.mfazio.wwc2023.services

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dev.mfazio.wwc2023.types.db.TeamWithPointsDbModel
import dev.mfazio.wwc2023.types.domain.Team
import dev.mfazio.wwc2023.types.domain.TeamWithPoints

object TeamService {
    private const val eliminatedTeamsPath = "/eliminatedTeams"

    private val eliminatedTeamsRef = FirebaseAdmin.db.getReference(eliminatedTeamsPath)

    private var eliminatedTeams: Map<String, Boolean> = emptyMap()

    init {
        eliminatedTeamsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot?) {
                snapshot?.children?.toList()?.let { dbEliminatedTeams ->
                    eliminatedTeams = dbEliminatedTeams.associate {
                        it.key to it.getValue(Boolean::class.java)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError?) {
                println("Eliminated teams read failed: ${error?.code}")
            }
        })
    }

    fun saveTeamPoints(teamsWithPoints: Map<String, TeamWithPoints>): Boolean {
        val dbTeamsWithPoints =
            teamsWithPoints.mapValues { (_, teamWithPoints) -> TeamWithPointsDbModel.fromTeamWithPoints(teamWithPoints) }

        return try {
            FirebaseAdmin.db
                .getReference("points")
                .setValueAsync(dbTeamsWithPoints)
                .get()

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun isTeamEliminated(team: Team) = isTeamEliminated(team.teamId)

    fun isTeamEliminated(teamId: String) = eliminatedTeams.getOrDefault(teamId, false)
}
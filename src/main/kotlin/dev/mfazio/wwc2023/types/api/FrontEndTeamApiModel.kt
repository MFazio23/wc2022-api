package dev.mfazio.wwc2023.types.api

import dev.mfazio.wwc2023.types.domain.Team
import kotlinx.serialization.Serializable

@Serializable
data class FrontEndTeamApiModel(
    val teamId: String,
    val teamName: String,
    val eloCode: String = "",
    val group: String,
) {
    companion object {
        fun fromTeam(team: Team) = FrontEndTeamApiModel(
            teamId = team.teamId,
            teamName = team.teamName,
            eloCode = team.eloCode,
            group = team.group
        )

        fun allTeams() = Team.allTeams.associate { team ->
            team.teamId to fromTeam(team)
        }
    }
}
package dev.mfazio.wc2022.types.api

import dev.mfazio.wc2022.types.domain.Team
import kotlinx.serialization.Serializable

@Serializable
data class FrontEndTeamApiModel(
    val teamId: String,
    val teamName: String,
    val flagCode: String = "",
    val group: String,
) {
    companion object {
        fun fromTeam(team: Team) = FrontEndTeamApiModel(
            teamId = team.teamId,
            teamName = team.teamName,
            flagCode = team.flagCode,
            group = team.group
        )

        fun allTeams() = Team.allTeams.associate { team ->
            team.teamId to fromTeam(team)
        }
    }
}
package dev.mfazio.wc2022.types.api

import dev.mfazio.wc2022.types.domain.Team
import kotlinx.serialization.Serializable

@Serializable
data class TeamApiModel(
    val teamId: String,
    val teamName: String,
    val group: String,
) {
    companion object {
        fun fromTeam(team: Team) = TeamApiModel(
            teamId = team.teamId,
            teamName = team.teamName,
            group = team.group,
        )

        fun allTeams() = Team.allTeams.map(::fromTeam)
    }
}

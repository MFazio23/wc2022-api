package dev.mfazio.wc2022.types.db

import dev.mfazio.wc2022.types.domain.Player
import dev.mfazio.wc2022.types.domain.PlayerWithTeams
import dev.mfazio.wc2022.types.domain.Team

data class PlayerWithTeamsDbModel(
    val id: String? = null,
    val name: String? = null,
    val teams: Map<String, String?> = emptyMap(),
) {
    fun toPlayerWithTeams() =
        if (id == null || name == null) {
            null
        } else {
            PlayerWithTeams(
                player = Player(
                    id = id,
                    name = name,
                ),
                teams = teams.keys.mapNotNull { teamId ->
                    Team.getTeamById(teamId)
                }
            )
        }

    companion object {
        fun fromPlayerWithTeams(playerWithTeams: PlayerWithTeams) =
            PlayerWithTeamsDbModel(
                id = playerWithTeams.player.id,
                name = playerWithTeams.player.name,
                teams = playerWithTeams.teams.associate { team ->
                    team.teamId to team.teamName
                }
            )
    }
}
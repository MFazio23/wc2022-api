package dev.mfazio.wc2022.types.db

import dev.mfazio.wc2022.types.domain.Player
import dev.mfazio.wc2022.types.domain.PlayerWithTeams
import dev.mfazio.wc2022.types.domain.Team
import dev.mfazio.wc2022.types.domain.TeamWithPoints

data class PlayerWithTeamsDbModel(
    val id: String? = null,
    val name: String? = null,
    val teams: List<String?> = emptyList(),
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
                teams = teams.map { teamId ->
                    Team.getTeamById(teamId)?.let { team ->
                        TeamWithPoints(
                            team = team
                        )
                    }
                }
            )
        }

    companion object {
        fun fromPlayerWithTeams(playerWithTeams: PlayerWithTeams) =
            PlayerWithTeamsDbModel(
                id = playerWithTeams.player.id,
                name = playerWithTeams.player.name,
                teams = playerWithTeams.teams.mapNotNull { it?.team?.teamId }
            )
    }
}
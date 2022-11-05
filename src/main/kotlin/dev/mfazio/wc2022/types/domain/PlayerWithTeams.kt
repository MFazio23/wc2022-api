package dev.mfazio.wc2022.types.domain

data class PlayerWithTeams(
    val player: Player,
    val teams: List<TeamWithPoints?> = emptyList(),
)
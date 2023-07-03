package dev.mfazio.wwc2023.types.domain

data class PlayerWithTeams(
    val player: Player,
    val teams: List<Team> = emptyList(),
)
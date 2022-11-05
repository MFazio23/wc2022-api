package dev.mfazio.wc2022.types.domain

data class Party(
    val name: String,
    val code: String,
    val owner: Player,
    val playersWithTeams: List<PlayerWithTeams?> = emptyList(),
    val isDeleted: Boolean = false,
)
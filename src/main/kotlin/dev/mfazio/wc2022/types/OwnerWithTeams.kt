package dev.mfazio.wc2022.types

data class OwnerWithTeams(
    val ownerId: String,
    val ownerName: String,
    val teams: List<TeamWithPoints> = emptyList(),
)
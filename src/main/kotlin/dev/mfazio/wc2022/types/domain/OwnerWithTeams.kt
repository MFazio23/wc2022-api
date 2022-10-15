package dev.mfazio.wc2022.types.domain

data class OwnerWithTeams(
    val ownerId: String,
    val ownerName: String,
    val teams: List<TeamWithPoints> = emptyList(),
)
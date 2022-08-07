package dev.mfazio.wc2022.types

data class Party(
    val name: String,
    val partyCode: String,
    val ownersWithTeams: List<OwnerWithTeams>
)

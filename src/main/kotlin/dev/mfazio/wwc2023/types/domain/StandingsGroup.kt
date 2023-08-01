package dev.mfazio.wwc2023.types.domain

data class StandingsGroup(
    val groupName: String,
    val standings: Map<Int, TeamWithPoints?>,
)

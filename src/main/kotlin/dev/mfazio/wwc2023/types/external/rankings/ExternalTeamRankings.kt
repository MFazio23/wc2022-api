package dev.mfazio.wwc2023.types.external.rankings

import kotlinx.serialization.Serializable

@Serializable
data class ExternalTeamRankings(
    val rankings: List<ExternalTeamRanking>
)

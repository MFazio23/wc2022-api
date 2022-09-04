package dev.mfazio.wc2022.types.external.rankings

import kotlinx.serialization.Serializable

@Serializable
data class ExternalTeamRankings(
    val rankings: List<ExternalTeamRanking>
)

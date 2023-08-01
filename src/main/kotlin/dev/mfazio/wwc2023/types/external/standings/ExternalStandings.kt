package dev.mfazio.wwc2023.types.external.standings


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalStandings(
    @SerialName("Results")
    val results: List<ExternalStanding>
)
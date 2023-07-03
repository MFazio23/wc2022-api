package dev.mfazio.wwc2023.types.external.schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalSchedule(
    @SerialName("Results")
    val results: List<ExternalResult>
)
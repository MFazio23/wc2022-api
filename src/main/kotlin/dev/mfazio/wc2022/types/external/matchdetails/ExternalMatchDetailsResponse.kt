package dev.mfazio.wc2022.types.external.matchdetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMatchDetailsResponse(
    @SerialName("matchDetails")
    val matchDetails: ExternalMatchDetails,
)
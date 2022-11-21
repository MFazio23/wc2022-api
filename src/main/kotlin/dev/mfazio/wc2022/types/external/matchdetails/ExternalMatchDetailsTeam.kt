package dev.mfazio.wc2022.types.external.matchdetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMatchDetailsTeam(
    @SerialName("Abbreviation")
    val abbreviation: String,
    @SerialName("IdCountry")
    val idCountry: String,
    @SerialName("IdTeam")
    val idTeam: String,
    @SerialName("Score")
    val score: Int,
)
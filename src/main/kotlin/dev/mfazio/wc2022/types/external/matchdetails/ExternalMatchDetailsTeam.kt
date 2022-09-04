package dev.mfazio.wc2022.types.external.matchdetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMatchDetailsTeam(
    @SerialName("abbreviation")
    val abbreviation: String,
    @SerialName("formation")
    val formation: String,
    @SerialName("goals")
    val goals: Int,
    @SerialName("idCountry")
    val idCountry: String,
    @SerialName("idTeam")
    val idTeam: String,
    @SerialName("pictureUrl")
    val pictureUrl: String,
    @SerialName("score")
    val score: Int,
    @SerialName("teamName")
    val teamName: String
)
package dev.mfazio.wwc2023.types.external.schedule


import dev.mfazio.wwc2023.types.external.ExternalLocaleDescription
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalTeam(
    @SerialName("Abbreviation")
    val abbreviation: String,
    @SerialName("IdAssociation")
    val idAssociation: String,
    @SerialName("IdCountry")
    val idCountry: String?,
    @SerialName("IdTeam")
    val idTeam: String,
    @SerialName("ShortClubName")
    val shortClubName: String,
    @SerialName("TeamName")
    val teamName: List<ExternalLocaleDescription>,
)
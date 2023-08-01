package dev.mfazio.wwc2023.types.external.standings


import dev.mfazio.wwc2023.types.external.ExternalLocaleDescription
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalTeam(
    @SerialName("Abbreviation")
    val abbreviation: String,
    @SerialName("AgeType")
    val ageType: Int,
    @SerialName("City")
    val city: String,
    @SerialName("DisplayName")
    val displayName: List<ExternalLocaleDescription>,
    @SerialName("FootballType")
    val footballType: Int,
    @SerialName("FoundationYear")
    val foundationYear: Int,
    @SerialName("Gender")
    val gender: Int,
    @SerialName("IdAssociation")
    val idAssociation: String,
    @SerialName("IdConfederation")
    val idConfederation: String,
    @SerialName("IdCountry")
    val idCountry: String?,
    @SerialName("IdTeam")
    val idTeam: String,
    @SerialName("Name")
    val name: List<ExternalLocaleDescription>,
    @SerialName("PictureUrl")
    val pictureUrl: String,
    @SerialName("PostalCode")
    val postalCode: String,
    @SerialName("ShortClubName")
    val shortClubName: String,
    @SerialName("Street")
    val street: String,
    @SerialName("Type")
    val type: Int
)
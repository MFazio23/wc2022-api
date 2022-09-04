package dev.mfazio.wc2022.types.external.schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalScheduleTeam(
    @SerialName("abbreviation")
    val abbreviation: String?,
    @SerialName("confederationId")
    val confederationId: String?,
    @SerialName("countryId")
    val countryId: String?,
    @SerialName("footballType")
    val footballType: Int?,
    @SerialName("gender")
    val gender: Int?,
    @SerialName("teamId")
    val teamId: String?,
    @SerialName("teamName")
    val teamName: String?,
    @SerialName("teamType")
    val teamType: Int?
)
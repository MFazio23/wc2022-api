package dev.mfazio.wc2022.types.external.schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalScheduleCompetition(
    @SerialName("activeSeasons")
    val activeSeasons: List<ExternalScheduleActiveSeason>?,
    @SerialName("competitionDetailsUrl")
    val competitionDetailsUrl: String?,
    @SerialName("competitionId")
    val competitionId: String?,
    @SerialName("competitionType")
    val competitionType: Int?,
    @SerialName("footballType")
    val footballType: Int?,
    @SerialName("gender")
    val gender: Int?,
    @SerialName("name")
    val name: String?
)
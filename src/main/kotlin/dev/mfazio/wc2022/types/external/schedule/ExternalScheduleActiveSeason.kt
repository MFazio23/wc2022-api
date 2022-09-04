package dev.mfazio.wc2022.types.external.schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalScheduleActiveSeason(
    @SerialName("competitionId")
    val competitionId: String?,
    @SerialName("endDate")
    val endDate: String?,
    @SerialName("matches")
    val matches: List<ExternalScheduleMatch>?,
    @SerialName("name")
    val name: String?,
    @SerialName("seasonId")
    val seasonId: String?,
    @SerialName("startDate")
    val startDate: String?
)
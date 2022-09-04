package dev.mfazio.wc2022.types.external.schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalScheduleMatch(
    @SerialName("away")
    val away: ExternalScheduleTeam? = null,
    @SerialName("competitionName")
    val competitionName: String? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("groupName")
    val groupName: String? = null,
    @SerialName("home")
    val home: ExternalScheduleTeam? = null,
    @SerialName("idCompetition")
    val idCompetition: String? = null,
    @SerialName("idGroup")
    val idGroup: String? = null,
    @SerialName("idMatch")
    val idMatch: String? = null,
    @SerialName("idSeason")
    val idSeason: String? = null,
    @SerialName("idStage")
    val idStage: String? = null,
    @SerialName("matchDetailsSectionUrl")
    val matchDetailsSectionUrl: String? = null,
    @SerialName("matchDetailsUrl")
    val matchDetailsUrl: String? = null,
    @SerialName("matchNumber")
    val matchNumber: Int? = null,
    @SerialName("matchStatus")
    val matchStatus: Int? = null,
    @SerialName("stadiumCityName")
    val stadiumCityName: String? = null,
    @SerialName("stadiumName")
    val stadiumName: String? = null,
    @SerialName("placeHolderA")
    val placeholderA: String? = null,
    @SerialName("placeHolderB")
    val placeholderB: String? = null,
)
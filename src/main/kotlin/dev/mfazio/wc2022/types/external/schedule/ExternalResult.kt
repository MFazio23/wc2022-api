package dev.mfazio.wc2022.types.external.schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalResult(
    @SerialName("Away")
    val away: ExternalTeam?,
    @SerialName("CompetitionName")
    val competitionName: List<ExternalLocaleDescription>,
    @SerialName("Date")
    val dateTime: String,
    @SerialName("GroupName")
    val groupName: List<ExternalLocaleDescription>?,
    @SerialName("Home")
    val home: ExternalTeam?,
    @SerialName("IdCompetition")
    val idCompetition: String,
    @SerialName("IdGroup")
    val idGroup: String?,
    @SerialName("IdMatch")
    val idMatch: String,
    @SerialName("IdSeason")
    val idSeason: String,
    @SerialName("IdStage")
    val idStage: String,
    @SerialName("LocalDate")
    val localDate: String,
    @SerialName("MatchNumber")
    val matchNumber: Int,
    @SerialName("MatchStatus")
    val matchStatus: Int,
    @SerialName("PlaceHolderA")
    val placeHolderA: String,
    @SerialName("PlaceHolderB")
    val placeHolderB: String,
    @SerialName("ResultType")
    val resultType: Int,
    @SerialName("SeasonName")
    val seasonName: List<ExternalLocaleDescription>,
    @SerialName("Stadium")
    val stadium: ExternalStadium,
    @SerialName("StageName")
    val stageName: List<ExternalLocaleDescription>,
)
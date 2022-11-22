package dev.mfazio.wc2022.types.external.schedule


import dev.mfazio.wc2022.types.domain.MatchStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalResult(
    @SerialName("Away")
    val away: ExternalTeam?,
    @SerialName("AwayTeamScore")
    val awayScore: Int?,
    @SerialName("CompetitionName")
    val competitionName: List<ExternalLocaleDescription>,
    @SerialName("Date")
    val dateTime: String,
    @SerialName("GroupName")
    val groupName: List<ExternalLocaleDescription>?,
    @SerialName("Home")
    val home: ExternalTeam?,
    @SerialName("HomeTeamScore")
    val homeScore: Int?,
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
    val matchStatusInt: Int,
    @SerialName("MatchTime")
    val matchTime: String?,
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
) {
    val matchStatus: MatchStatus = when(matchStatusInt) {
        0 -> MatchStatus.Played
        1 -> MatchStatus.ToBePlayed
        3 -> MatchStatus.Live
        4 -> MatchStatus.Abandoned
        7 -> MatchStatus.Postponed
        8 -> MatchStatus.Cancelled
        9 -> MatchStatus.Forfeited
        12 -> MatchStatus.LineUps
        99 -> MatchStatus.Suspended
        else -> MatchStatus.Unknown
    }
}
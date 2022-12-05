package dev.mfazio.wc2022.types.external.matchdetails


import dev.mfazio.wc2022.types.domain.MatchStatus
import dev.mfazio.wc2022.types.external.ExternalLocaleDescription
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMatchDetails(
    @SerialName("AwayTeam")
    val awayTeam: ExternalMatchDetailsTeam,
    @SerialName("AwayTeamPenaltyScore")
    val awayTeamPenaltyScore: Int? = null,
    @SerialName("Date")
    val date: String,
    @SerialName("HomeTeam")
    val homeTeam: ExternalMatchDetailsTeam,
    @SerialName("HomeTeamPenaltyScore")
    val homeTeamPenaltyScore: Int? = null,
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
    @SerialName("MatchStatus")
    val matchStatusInt: Int,
    @SerialName("MatchTime")
    val matchTime: String,
    @SerialName("Period")
    val period: Int,
    @SerialName("Stadium")
    val stadium: ExternalMatchDetailsStadium,
    @SerialName("ResultType")
    val resultType: Int,
    @SerialName("GroupName")
    val groupName: List<ExternalLocaleDescription>,
    @SerialName("Winner")
    val winner: String?
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
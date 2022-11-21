package dev.mfazio.wc2022.types.external.matchdetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMatchDetails(
    @SerialName("AwayTeam")
    val awayTeam: ExternalMatchDetailsTeam,
    @SerialName("AwayTeamPenaltyScore")
    val awayTeamPenaltyScore: Int,
    @SerialName("Date")
    val date: String,
    @SerialName("HomeTeam")
    val homeTeam: ExternalMatchDetailsTeam,
    @SerialName("HomeTeamPenaltyScore")
    val homeTeamPenaltyScore: Int,
    @SerialName("IdCompetition")
    val idCompetition: String,
    @SerialName("IdGroup")
    val idGroup: String,
    @SerialName("IdMatch")
    val idMatch: String,
    @SerialName("IdSeason")
    val idSeason: String,
    @SerialName("IdStage")
    val idStage: String,
    @SerialName("LocalDate")
    val localDate: String,
    @SerialName("MatchStatus")
    val matchStatus: Int,
    @SerialName("MatchTime")
    val matchTime: String,
    @SerialName("Period")
    val period: Int,
    @SerialName("ResultType")
    val resultType: Int,
    @SerialName("Winner")
    val winner: String?
)
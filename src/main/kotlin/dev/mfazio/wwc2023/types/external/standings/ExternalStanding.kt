package dev.mfazio.wwc2023.types.external.standings


import dev.mfazio.wwc2023.types.external.ExternalLocaleDescription
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalStanding(
    @SerialName("Against")
    val against: Int,
    @SerialName("Date")
    val date: String,
    @SerialName("Drawn")
    val drawn: Int,
    @SerialName("EndDate")
    val endDate: String,
    @SerialName("FairPlayCoefficient")
    val fairPlayCoefficient: Double,
    @SerialName("For")
    val forX: Int,
    @SerialName("GoalsDiference")
    val goalsDifference: Int,
    @SerialName("Group")
    val group: List<ExternalLocaleDescription>,
    @SerialName("IdCompetition")
    val idCompetition: String,
    @SerialName("IdGroup")
    val idGroup: String,
    @SerialName("IdSeason")
    val idSeason: String,
    @SerialName("IdStage")
    val idStage: String,
    @SerialName("IdTeam")
    val idTeam: String,
    @SerialName("Lost")
    val lost: Int,
    @SerialName("MatchDay")
    val matchDay: Int,
    @SerialName("Played")
    val played: Int,
    @SerialName("Points")
    val points: Int,
    @SerialName("Position")
    val position: Int,
    @SerialName("StartDate")
    val startDate: String,
    @SerialName("Team")
    val team: ExternalTeam,
    @SerialName("WinByExtraTime")
    val winByExtraTime: Int,
    @SerialName("WinByPenalty")
    val winByPenalty: Int,
    @SerialName("Won")
    val won: Int
)
package dev.mfazio.wc2022.types.external.matchdetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMatchDetails(
    @SerialName("awayTeam")
    val awayTeam: ExternalMatchDetailsTeam,
    @SerialName("awayTeamPenaltyScore")
    val awayTeamPenaltyScore: Int,
    @SerialName("city")
    val city: String,
    @SerialName("competitionName")
    val competitionName: String,
    @SerialName("currentMinute")
    val currentMinute: String,
    @SerialName("currentPeriod")
    val currentPeriod: Int,
    @SerialName("homeTeam")
    val homeTeam: ExternalMatchDetailsTeam,
    @SerialName("homeTeamPenaltyScore")
    val homeTeamPenaltyScore: Int,
    @SerialName("kickoffTime")
    val kickoffTime: String,
    @SerialName("location")
    val location: String,
    @SerialName("matchStatus")
    val matchStatus: Int,
    @SerialName("resultType")
    val resultType: Int
)
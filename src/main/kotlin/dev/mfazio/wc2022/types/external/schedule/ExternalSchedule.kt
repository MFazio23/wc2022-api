package dev.mfazio.wc2022.types.external.schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalSchedule(
    @SerialName("competition")
    val competition: ExternalScheduleCompetition?,
)
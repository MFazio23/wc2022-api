package dev.mfazio.wc2022.types.api

import kotlinx.serialization.Serializable

@Serializable
data class StatusApiModel(
    val partyCount: Int,
    val scheduledMatchCount: Int,
)

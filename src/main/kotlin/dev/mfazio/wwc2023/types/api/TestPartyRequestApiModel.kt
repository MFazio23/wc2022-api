package dev.mfazio.wwc2023.types.api

import kotlinx.serialization.Serializable

@Serializable
data class TestPartyRequestApiModel(
    val code: String? = null,
    val name: String? = null,
    val playersToInclude: List<PlayerApiModel> = emptyList()
)
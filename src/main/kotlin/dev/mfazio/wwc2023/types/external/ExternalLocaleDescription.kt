package dev.mfazio.wwc2023.types.external

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalLocaleDescription(
    @SerialName("Locale")
    val locale: String,
    @SerialName("Description")
    val description: String,
)

package dev.mfazio.wc2022.types.external

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalLocaleDescription(
    @SerialName("Locale")
    val locale: String,
    @SerialName("Description")
    val description: String,
)

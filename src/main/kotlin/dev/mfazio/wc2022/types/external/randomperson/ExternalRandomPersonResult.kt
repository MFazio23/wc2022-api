package dev.mfazio.wc2022.types.external.randomperson


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalRandomPersonResult(
    @SerialName("results")
    val results: List<ExternalRandomPerson>
)
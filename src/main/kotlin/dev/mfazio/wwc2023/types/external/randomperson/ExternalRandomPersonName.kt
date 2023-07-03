package dev.mfazio.wwc2023.types.external.randomperson


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalRandomPersonName(
    @SerialName("first")
    val first: String,
    @SerialName("last")
    val last: String,
    @SerialName("title")
    val title: String
) {
    val fullName = "$first $last"
}
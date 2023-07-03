package dev.mfazio.wwc2023.types.external.randomperson


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalRandomPerson(
    @SerialName("name")
    val name: ExternalRandomPersonName,
    @SerialName("login")
    val login: ExternalRandomPersonLogin,
)
package dev.mfazio.wwc2023.types.external.schedule


import dev.mfazio.wwc2023.types.external.ExternalLocaleDescription
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalStadium(
    @SerialName("CityName")
    val cityName: List<ExternalLocaleDescription>,
    @SerialName("IdCity")
    val idCity: String,
    @SerialName("IdCountry")
    val idCountry: String,
    @SerialName("IdStadium")
    val idStadium: String,
    @SerialName("Name")
    val name: List<ExternalLocaleDescription>,
    @SerialName("Roof")
    val roof: Boolean,
)
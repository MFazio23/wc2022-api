package dev.mfazio.wc2022.routing

import kotlinx.serialization.Serializable

@Serializable
data class APIResponse<T>(
    val message: String? = null,
    val data: T? = null,
    val error: String? = null,
) {
    companion object {
        val notYetImplemented = APIResponse<String>(message = "Not yet implemented")
    }
}
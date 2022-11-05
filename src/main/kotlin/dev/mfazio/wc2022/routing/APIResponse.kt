package dev.mfazio.wc2022.routing

import kotlinx.serialization.Serializable

@Serializable
data class APIResponse<T>(
    val data: T? = null,
    val error: String? = null,
) {
    companion object {
        val notYetImplemented = APIResponse<String>(error = "Not yet implemented")

        fun withError(error: String? = null) = APIResponse<String>(error = error)
    }
}
package dev.mfazio.wwc2023.auth

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class FirebaseAuthUser(
    val userId: String = "",
    val displayName: String = "",
) : Principal

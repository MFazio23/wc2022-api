package dev.mfazio.wc2022.auth

import io.ktor.server.auth.*

class FirebaseAuth(config: Config) : AuthenticationProvider(config) {
    override suspend fun onAuthenticate(context: AuthenticationContext) {
        TODO("Not yet implemented")
    }
}
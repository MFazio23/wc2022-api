package dev.mfazio.wc2022.auth

import com.google.firebase.auth.FirebaseToken
import io.ktor.http.auth.*
import io.ktor.server.application.*
import io.ktor.server.auth.*

class FirebaseConfig(name: String?) : AuthenticationProvider.Config(name) {
    internal var authHeader: (ApplicationCall) -> HttpAuthHeader? =
        { call -> call.request.parseAuthorizationHeader() }

    var firebaseAuthenticationFunction: AuthenticationFunction<FirebaseToken> = {
        throw NotImplementedError(FirebaseImplementationError)
    }

    fun validate(validate: suspend ApplicationCall.(FirebaseToken) -> FirebaseAuthUser?) {
        firebaseAuthenticationFunction = validate
    }

    companion object {
        private const val FirebaseImplementationError =
            "Firebase auth validate function is not specified, use firebase { validate { ... } }to fix"
    }
}
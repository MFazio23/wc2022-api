package dev.mfazio.wc2022.extensions

import dev.mfazio.wc2022.routing.APIResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*

/**
 * Retrieves an authenticated [Principal] for `this` call.
 */
inline fun <reified P : Principal> ApplicationCall.principal(): P? = authentication.principal()

suspend inline fun ApplicationCall.notYetImplemented() = this.respond(
    HttpStatusCode.InternalServerError,
    APIResponse.withError("Not yet implemented")
)

suspend inline fun ApplicationCall.badRequest(message: String? = null) = this.respond(
    HttpStatusCode.BadRequest,
    APIResponse.withError(message)
)

suspend inline fun <reified T : Any> ApplicationCall.ok(result: T?) = this.respond(
    APIResponse(
        data = result
    )
)

suspend fun ApplicationCall.noContent() = this.respond(HttpStatusCode.NoContent)

suspend fun ApplicationCall.unauthorized(message: String? = null) = this.respond(HttpStatusCode.Unauthorized, APIResponse.withError(message))
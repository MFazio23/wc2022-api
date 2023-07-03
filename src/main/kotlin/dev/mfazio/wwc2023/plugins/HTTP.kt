package dev.mfazio.wwc2023.plugins

import io.ktor.server.plugins.compression.*
import io.ktor.http.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.application.*

fun Application.configureHTTP() {
    install(Compression)
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHost("localhost:3000")
        allowHost(
            host = "mfazio.dev",
            schemes = listOf("https"),
            subDomains = listOf("wc2022", "wc2022-staging", "wwc2023", "wwc2023-staging")
        )
    }
}
package dev.mfazio.wc2022.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

open class ApiService {
    protected val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    protected suspend inline fun <reified T> getResultOrNull(url: String): T? {
        val response = client.get(url)

        return if (response.status == HttpStatusCode.OK) {
            response.body<T>()
        } else {
            null
        }
    }
}
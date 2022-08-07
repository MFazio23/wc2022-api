package dev.mfazio.wc2022.services

import dev.mfazio.wc2022.URLs
import dev.mfazio.wc2022.types.external.ExternalTeamRankings
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

object RankingsService {
    private val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getExternalRankings(): ExternalTeamRankings? {
        val response = client.get(URLs.rankingsUrl)

        return if (response.status == HttpStatusCode.OK) {
            response.body<ExternalTeamRankings>()
        } else {
            null
        }
    }
}
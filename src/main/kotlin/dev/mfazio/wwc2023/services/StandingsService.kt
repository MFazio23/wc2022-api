package dev.mfazio.wwc2023.services

import dev.mfazio.utils.extensions.filterNotNullValues
import dev.mfazio.wwc2023.URLs
import dev.mfazio.wwc2023.types.db.StandingsGroupDbModel
import dev.mfazio.wwc2023.types.domain.StandingsGroup
import dev.mfazio.wwc2023.types.external.standings.ExternalStandings

object StandingsService : ApiService() {
    fun saveStandingsToFirebase(standings: List<StandingsGroup>) {
        val filteredStandings = standings.map { (groupName, standings) ->
            StandingsGroup(
                groupName = groupName,
                standings = standings.filterNotNullValues()
            )
        }
        val dbStandings = filteredStandings.associate(StandingsGroupDbModel.Companion::fromStandingsGroup)
        FirebaseAdmin.db
            .getReference("/standings")
            .setValueAsync(dbStandings)
            .get()
    }

    suspend fun getExternalStandings(): ExternalStandings? = getResultOrNull(URLs.standingsUrl)
}
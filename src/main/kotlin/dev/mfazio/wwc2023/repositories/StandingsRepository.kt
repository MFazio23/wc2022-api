package dev.mfazio.wwc2023.repositories

import dev.mfazio.wwc2023.services.StandingsService
import dev.mfazio.wwc2023.types.domain.StandingsGroup

object StandingsRepository {

    suspend fun getStandings(): List<StandingsGroup>? =
        StandingsService.getExternalStandings()?.let { externalStandings ->
            val teamsWithPoints = TeamRepository.getTeamsWithPoints()
            externalStandings.results.groupBy { standing ->
                standing.group.first().description
            }.mapNotNull { (groupName, standings) ->
                StandingsGroup(
                    groupName = groupName,
                    standings = standings.associate { standing ->
                        standing.position to teamsWithPoints[standing.team.abbreviation]
                    }
                )
            }.filter { standingsGroup ->
                standingsGroup.standings.isNotEmpty()
            }
        }

    suspend fun updateStandings(): List<StandingsGroup> =
        getStandings()?.let { standings ->
            StandingsService.saveStandingsToFirebase(standings)
            standings
        } ?: emptyList()
}
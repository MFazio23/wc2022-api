package dev.mfazio.wwc2023.types.db

import dev.mfazio.wwc2023.types.domain.StandingsGroup
import kotlinx.serialization.Serializable

@Serializable
data class StandingsGroupDbModel(
    val groupName: String,
    val standings: Map<String, String?> = emptyMap(),
) {

    companion object {
        fun fromStandingsGroup(standingsGroup: StandingsGroup) = standingsGroup.groupName to StandingsGroupDbModel(
            groupName = standingsGroup.groupName,
            standings = standingsGroup.standings.entries.associate { (teamRank, teamWithPoints) ->
                teamRank.toString() to teamWithPoints?.team?.teamId
            }
        )
    }
}
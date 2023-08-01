package dev.mfazio.wwc2023.types.api

import dev.mfazio.wwc2023.types.domain.StandingsGroup
import kotlinx.serialization.Serializable

@Serializable
data class StandingsGroupApiModel(
    val groupName: String,
    val standings: Map<Int, String?> = emptyMap(),
) {
    companion object {
        fun fromStandingsGroup(standingsGroup: StandingsGroup) = StandingsGroupApiModel(
            groupName = standingsGroup.groupName,
            standings = standingsGroup.standings.mapValues { (_, teamWithPoints) -> teamWithPoints?.team?.teamId }
        )
    }
}
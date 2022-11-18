package dev.mfazio.wc2022.types.db

import dev.mfazio.wc2022.types.domain.Party

data class PartyDbModel(
    val name: String? = null,
    val code: String? = null,
    val owner: PlayerDbModel? = null,
    val players: Map<String, PlayerWithTeamsDbModel> = emptyMap(),
    @field:JvmField val isDeleted: Boolean? = false,
) {
    fun toParty() = this.owner?.toPlayer()?.let { owner ->
        Party(
            name = this.name.orEmpty(),
            code = this.code.orEmpty(),
            owner = owner,
            playersWithTeams = this.players.values.map { playerDbModel ->
                playerDbModel.toPlayerWithTeams()
            },
            isDeleted = isDeleted ?: false
        )
    }

    companion object {
        fun fromParty(party: Party) = PartyDbModel(
            name = party.name,
            code = party.code,
            owner = PlayerDbModel.fromPlayer(party.owner),
            players = party.playersWithTeams.filterNotNull().associate { it.player.id to PlayerWithTeamsDbModel.fromPlayerWithTeams(it) },
            isDeleted = party.isDeleted,
        )
    }
}
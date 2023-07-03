package dev.mfazio.wwc2023.types.db

import dev.mfazio.wwc2023.types.domain.Player

data class PlayerDbModel(
    val id: String? = null,
    val name: String? = null,
) {
    fun toPlayer() = this.id?.let {
        Player(
            id = this.id,
            name = this.name.orEmpty(),
        )
    }

    companion object {
        fun fromPlayer(player: Player) = PlayerDbModel(
            id = player.id,
            name = player.name,
        )
    }
}

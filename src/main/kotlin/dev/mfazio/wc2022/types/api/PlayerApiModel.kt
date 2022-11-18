package dev.mfazio.wc2022.types.api

import dev.mfazio.wc2022.types.domain.Player
import kotlinx.serialization.Serializable

@Serializable
data class PlayerApiModel(
    val id: String,
    val name: String,
) {
    fun toPlayer() = Player(
        id = id,
        name = name,
    )

    companion object {
        fun fromPlayer(player: Player) = PlayerApiModel(
            id = player.id,
            name = player.name,
        )
    }
}
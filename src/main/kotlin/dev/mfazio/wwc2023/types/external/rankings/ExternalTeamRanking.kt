package dev.mfazio.wwc2023.types.external.rankings

import kotlinx.serialization.Serializable

@Serializable
data class ExternalTeamRanking(
    val rankingItem: RankingItem,
    val previousPoints: Double,
    val tag: Tag,
) {
    @Serializable
    data class RankingItem(
        val rank: Int,
        val flag: Flag,
        val name: String,
        val totalPoints: Double,
        val active: Boolean,
        val previousRank: Int,
        val countryURL: String,
        val countryCode: String,
    )

    @Serializable
    data class Flag(
        val src: String,
        val width: Int,
        val height: Int,
        val title: String,
        val alt: String,
    )

    @Serializable
    data class Tag(
        val id: String,
        val text: String,
    )
}
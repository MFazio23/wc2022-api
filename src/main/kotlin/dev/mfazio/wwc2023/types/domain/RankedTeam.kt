package dev.mfazio.wwc2023.types.domain

data class RankedTeam(
    val team: Team,
    val fifaRanking: Ranking? = null,
) {
    fun getRanking(rankingType: RankingType) =
        when(rankingType) {
            RankingType.FIFA -> fifaRanking?.ranking
            else -> 1
        }
}
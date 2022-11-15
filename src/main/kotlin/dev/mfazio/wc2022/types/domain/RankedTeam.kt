package dev.mfazio.wc2022.types.domain

data class RankedTeam(
    val team: Team,
    val fifaRanking: Ranking? = null,
    val eloRanking: Ranking? = null,
) {
    fun getRanking(rankingType: RankingType) =
        when(rankingType) {
            RankingType.FIFA -> fifaRanking?.ranking
            RankingType.ELO -> eloRanking?.ranking
            else -> 1
        }
}
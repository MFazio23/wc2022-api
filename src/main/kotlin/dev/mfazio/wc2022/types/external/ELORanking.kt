package dev.mfazio.wc2022.types.external

data class ELORanking(
    val tournamentRank: Int,
    val overallRank: Int,
    val teamELOCode: String,
    val rating: Int,
)

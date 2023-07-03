package dev.mfazio.wwc2023.types.external

data class ELORanking(
    val tournamentRank: Int,
    val overallRank: Int,
    val teamELOCode: String,
    val rating: Int,
)

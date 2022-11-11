package dev.mfazio.wc2022.types.domain

sealed class Team(
    val teamId: String,
    val teamName: String,
    val eloCode: String,
    val group: String,
    val altIds: List<String> = listOf(teamId)
) {
    object Argentina : Team("ARG", "Argentina", "AR", "C")
    object Australia : Team("AUS", "Australia", "AU", "D")
    object Belgium : Team("BEL", "Belgium", "BE", "F")
    object Brazil : Team("BRA", "Brazil", "BR", "G")
    object Cameroon : Team("CMR", "Cameroon", "CM", "G")
    object Canada : Team("CAN", "Canada", "CA", "F")
    object CostaRica : Team("CRC", "Costa Rica", "CR", "E")
    object Croatia : Team("CRO", "Croatia", "HR", "F")
    object Denmark : Team("DEN", "Denmark", "DK", "D")
    object Ecuador : Team("ECU", "Ecuador", "EC", "A")
    object England : Team("ENG", "England", "EN", "B")
    object France : Team("FRA", "France", "FR", "D")
    object Germany : Team("GER", "Germany", "DE", "E")
    object Ghana : Team("GHA", "Ghana", "GH", "H")
    object Iran : Team("IRN", "IR Iran", "IR", "B")
    object Japan : Team("JPN", "Japan", "JP", "E")
    object KoreaRepublic : Team("KOR", "Korea Republic", "KR", "H")
    object Mexico : Team("MEX", "Mexico", "MX", "C")
    object Morocco : Team("MAR", "Morocco", "MA", "F")
    object Netherlands : Team("NED", "Netherlands", "NL", "A")
    object Poland : Team("POL", "Poland", "PL", "C")
    object Portugal : Team("POR", "Portugal", "PT", "H")
    object Qatar : Team("QAT", "Qatar", "QA", "A")
    object SaudiArabia : Team("KSA", "Saudi Arabia", "SA", "C")
    object Senegal : Team("SEN", "Senegal", "SN", "A")
    object Serbia : Team("SRB", "Serbia", "RS", "G")
    object Spain : Team("ESP", "Spain", "ES", "E")
    object Switzerland : Team("SUI", "Switzerland", "CH", "G")
    object Tunisia : Team("TUN", "Tunisia", "TN", "D")
    object Uruguay : Team("URU", "Uruguay", "UY", "H")
    object USA : Team("USA", "USA", "US", "B")
    object Wales : Team("WAL", "Wales", "WA", "B")

    companion object {
        val allTeams = listOf(
            Argentina,
            Australia,
            Belgium,
            Brazil,
            Cameroon,
            Canada,
            CostaRica,
            Croatia,
            Denmark,
            Ecuador,
            England,
            France,
            Germany,
            Ghana,
            Iran,
            Japan,
            KoreaRepublic,
            Mexico,
            Morocco,
            Netherlands,
            Poland,
            Portugal,
            Qatar,
            SaudiArabia,
            Senegal,
            Serbia,
            Spain,
            Switzerland,
            Tunisia,
            Uruguay,
            USA,
            Wales,
        )

        fun getTeamById(teamId: String?) = allTeams.find { it.teamId == teamId || it.altIds.contains(teamId) }
    }
}
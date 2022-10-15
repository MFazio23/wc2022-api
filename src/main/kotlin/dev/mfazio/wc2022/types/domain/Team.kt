package dev.mfazio.wc2022.types.domain

sealed class Team(
    val teamId: String,
    val teamName: String,
    val group: String,
    val altIds: List<String> = listOf(teamId)
) {
    object Argentina : Team("ARG", "Argentina", "C")
    object Australia : Team("AUS", "Australia", "D")
    object Belgium : Team("BEL", "Belgium", "F")
    object Brazil : Team("BRA", "Brazil", "G")
    object Cameroon : Team("CMR", "Cameroon", "G")
    object Canada : Team("CAN", "Canada", "F")
    object CostaRica : Team("CRC", "Costa Rica", "E")
    object Croatia : Team("CRO", "Croatia", "F")
    object Denmark : Team("DEN", "Denmark", "D")
    object Ecuador : Team("ECU", "Ecuador", "A")
    object England : Team("ENG", "England", "B")
    object France : Team("FRA", "France", "D")
    object Germany : Team("GER", "Germany", "E")
    object Ghana : Team("GHA", "Ghana", "H")
    object Iran : Team("IRN", "IR Iran", "B")
    object Japan : Team("JPN", "Japan", "E")
    object KoreaRepublic : Team("KOR", "Korea Republic", "H")
    object Mexico : Team("MEX", "Mexico", "C")
    object Morocco : Team("MAR", "Morocco", "F")
    object Netherlands : Team("NED", "Netherlands", "A")
    object Poland : Team("POL", "Poland", "C")
    object Portugal : Team("POR", "Portugal", "H")
    object Qatar : Team("QAT", "Qatar", "A")
    object SaudiArabia : Team("KSA", "Saudi Arabia", "C")
    object Senegal : Team("SEN", "Senegal", "A")
    object Serbia : Team("SRB", "Serbia", "G")
    object Spain : Team("ESP", "Spain", "E")
    object Switzerland : Team("SUI", "Switzerland", "G")
    object Tunisia : Team("TUN", "Tunisia", "D")
    object Uruguay : Team("URU", "Uruguay", "H")
    object USA : Team("USA", "USA", "B")
    object Wales : Team("WAL", "Wales", "B")

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

        fun getTeamById(teamId: String) = allTeams.find { it.teamId == teamId || it.altIds.contains(teamId) }
    }
}
package dev.mfazio.wc2022.types.domain

sealed class Team(
    val teamName: String,
    val teamId: String,
    val group: String,
    val altIds: List<String> = listOf(teamId)
) {
    object Argentina : Team("Argentina", "ARG", "C")
    object Australia : Team("Australia", "AUS", "D")
    object Belgium : Team("Belgium", "BEL", "F")
    object Brazil : Team("Brazil", "BRA", "G")
    object Cameroon : Team("Cameroon", "CMR", "G")
    object Canada : Team("Canada", "CAN", "F")
    object CostaRica : Team("Costa Rica", "CRC", "E")
    object Croatia : Team("Croatia", "CRO", "F")
    object Denmark : Team("Denmark", "DEN", "D")
    object Ecuador : Team("Ecuador", "ECU", "A")
    object England : Team("England", "ENG", "B")
    object France : Team("France", "FRA", "D")
    object Germany : Team("Germany", "GER", "E")
    object Ghana : Team("Ghana", "GHA", "H")
    object Iran : Team("IR Iran", "IRN", "B")
    object Japan : Team("Japan", "JPN", "E")
    object KoreaRepublic : Team("Korea Republic", "KOR", "H")
    object Mexico : Team("Mexico", "MEX", "C")
    object Morocco : Team("Morocco", "MAR", "F")
    object Netherlands : Team("Netherlands", "NED", "A")
    object Poland : Team("Poland", "POL", "C")
    object Portugal : Team("Portugal", "POR", "H")
    object Qatar : Team("Qatar", "QAT", "A")
    object SaudiArabia : Team("Saudi Arabia", "KSA", "C")
    object Senegal : Team("Senegal", "SEN", "A")
    object Serbia : Team("Serbia", "SRB", "G")
    object Spain : Team("Spain", "ESP", "E")
    object Switzerland : Team("Switzerland", "SUI", "G")
    object Tunisia : Team("Tunisia", "TUN", "D")
    object Uruguay : Team("Uruguay", "URU", "H")
    object USA : Team("USA", "USA", "B")
    object Wales : Team("Wales", "WAL", "B")

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
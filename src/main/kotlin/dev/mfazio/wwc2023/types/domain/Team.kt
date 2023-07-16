package dev.mfazio.wwc2023.types.domain

sealed class Team(
    val teamId: String,
    val teamName: String,
    val shortCode: String,
    val group: String,
    val altIds: List<String> = listOf(teamId)
) {
    object Argentina : Team("ARG", "Argentina", "AR", "G")
    object Australia : Team("AUS", "Australia", "AU", "B")
    object Brazil : Team("BRA", "Brazil", "BR", "F")
    object Canada : Team("CAN", "Canada", "CA", "B")
    object China : Team("CHN", "China PR", "CN", "D")
    object Colombia : Team("COL", "Colombia", "CO", "H")
    object CostaRica : Team("CRC", "Costa Rica", "CR", "C")
    object Denmark : Team("DEN", "Denmark", "DK", "D")
    object England : Team("ENG", "England", "GB", "D")
    object France : Team("FRA", "France", "FR", "F")
    object Germany : Team("GER", "Germany", "DE", "H")
    object Haiti : Team("HAI", "Haiti", "HT", "D")
    object Italy : Team("ITA", "Italy", "IT", "G")
    object Jamaica : Team("JAM", "Jamaica", "JM", "F")
    object Japan : Team("JPN", "Japan", "JP", "C")
    object KoreaRepublic : Team("KOR", "Korea Republic", "KR", "H")
    object Morocco : Team("MAR", "Morocco", "MA", "H")
    object Netherlands : Team("NED", "Netherlands", "NL", "E")
    object NewZealand : Team("NZL", "New Zealand", "NZ", "A")
    object Nigeria : Team("NGA", "Nigeria", "NG", "B")
    object Norway : Team("NOR", "Norway", "NO", "A")
    object Panama : Team("PAN", "Panama", "PA", "F")
    object Philippines : Team("PHI", "Philippines", "PH", "A")
    object Portugal : Team("POR", "Portugal", "PT", "E")
    object Ireland : Team("IRL", "Ireland", "IE", "B")
    object SouthAfrica : Team("RSA", "South Africa", "ZA", "G")
    object Spain : Team("ESP", "Spain", "ES", "C")
    object Sweden : Team("SWE", "Sweden", "SE", "G")
    object Switzerland : Team("SUI", "Switzerland", "CH", "A")
    object USA : Team("USA", "USA", "US", "E")
    object Vietnam : Team("VIE", "Vietnam", "VN", "E")
    object Zambia : Team("ZAM", "Zambia", "ZM", "C")

    companion object {
        val allTeams = listOf(
            Argentina,
            Australia,
            Brazil,
            Canada,
            China,
            Colombia,
            CostaRica,
            Denmark,
            England,
            France,
            Germany,
            Haiti,
            Italy,
            Jamaica,
            Japan,
            KoreaRepublic,
            Morocco,
            Netherlands,
            NewZealand,
            Nigeria,
            Norway,
            Panama,
            Philippines,
            Portugal,
            Ireland,
            SouthAfrica,
            Spain,
            Sweden,
            Switzerland,
            USA,
            Vietnam,
            Zambia
        )

        fun getTeamById(teamId: String?) = allTeams.find { it.teamId == teamId || it.altIds.contains(teamId) }
    }
}
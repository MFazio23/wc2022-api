package dev.mfazio.wc2022.repositories

import dev.mfazio.wc2022.services.PartyService
import dev.mfazio.wc2022.types.domain.*

object PartyRepository {

    fun getPartyByCode(partyCode: String): Party? =
        PartyService.getPartyByCode(partyCode)

    fun getPartiesForUser(userId: String?): List<Party> =
        userId?.let {
            PartyService.getPartiesForUser(userId, includeDeleted = false)
        } ?: emptyList()

    fun getPartyCount() = PartyService.getPartyCount()

    fun createParty(
        name: String? = null,
        code: String? = null,
        ownerId: String,
        ownerName: String,
    ): Party? {
        val validCode = getValidPartyCode(code)

        val owner = Player(id = ownerId, name = ownerName)

        val party = Party(
            name = name ?: "$ownerName's party",
            code = validCode,
            owner = owner,
        )

        if (PartyService.savePartyToFirebase(party) == null) {
            println("Saving the party to Firebase failed.")
            return null
        }

        return PartyService.addPlayerToParty(
            partyCode = validCode,
            player = owner
        )
    }

    fun updatePartyName(code: String, name: String, ownerId: String): Party? {
        val party = PartyService.getPartyByCode(code)

        if (party?.owner?.id != ownerId) {
            return null
        }

        return PartyService.updatePartyName(code, name)
    }

    fun deleteParty(code: String, ownerId: String): Boolean = PartyService.getPartyByCode(code)?.let { party ->
        party.owner.id == ownerId && PartyService.deleteParty(partyCode = code)
    } ?: false

    suspend fun createTestParty(
        name: String? = null,
        code: String? = null,
        ownerId: String,
        ownerName: String,
        playerCount: Int,
        playersToInclude: List<Player>,
    ): Party? {
        val party = createParty(
            name = name,
            code = code,
            ownerId = ownerId,
            ownerName = ownerName,
        ) ?: return null

        playersToInclude.forEach { player ->
            addPlayerToParty(party.code, player)
        }

        PartyService.getRandomPlayers(playerCount - playersToInclude.size - 1).forEach { player ->
            addPlayerToParty(party.code, player)
        }

        return PartyService.getPartyByCode(party.code)
    }

    fun addPlayerToParty(partyCode: String, player: Player): Party? = if (PartyService.getPartyByCode(partyCode) != null) {
        PartyService.addPlayerToParty(partyCode, player)
    } else null

    fun removePlayerFromParty(partyCode: String, playerId: String): Party? = PartyService.removePlayerFromParty(partyCode, playerId)

    // Here comes the fun part.
    suspend fun distributeTeamsForParty(ownerId: String, partyCode: String, rankingType: RankingType, teamsPerUser: Int): Party? {
        val party = getPartyByCode(partyCode) ?: return null
        val players = party.playersWithTeams.filterNotNull()
        val totalTeamCount = teamsPerUser * players.size

        val teams = RankingsRepository
            .getTeamRankings()
            .filter { it.getRanking(rankingType) != null }
            .sortedBy { team ->
                team.getRanking(rankingType)
            }

        if (party.owner.id != ownerId || players.none() || teams.none() || teamsPerUser < 1 || totalTeamCount > teams.size) {
            println("Missing data")
            println("Is correct owner? ${party.owner.id == ownerId}")
            println("Player Count=${players.size}")
            println("Team Count=${teams.size}")
            println("Teams Per User=$teamsPerUser")
            println("Total team count=$totalTeamCount")
            return null
        }

        val chunkedTeams = teams.subList(0, totalTeamCount).chunked(players.size) { it.shuffled() }
        val playersWithTeams = players.mapIndexed { index, player ->
            player.copy(
                teams = chunkedTeams.fold(listOf()) { result, teams ->
                    teams.getOrNull(index)?.let { rankedTeam ->
                        result + rankedTeam.team
                    } ?: result
                }
            )
        }

        return PartyService.updateTeamsForParty(partyCode, playersWithTeams)
    }

    private fun getValidPartyCode(startingCode: String? = null): String {
        var code = startingCode

        while (code == null || PartyService.isCodeInUse(code)) {
            code = getRandomPartyCode()
        }

        return code
    }

    private fun getRandomPartyCode() = validPartyCodeCharacters
        .shuffled()
        .take(partyCodeLength)
        .joinToString("")


    private val validPartyCodeCharacters = "ABCDEFGHIJKLMNPQRSTUVWXYZ".toList()
    private const val partyCodeLength = 6
}
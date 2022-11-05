package dev.mfazio.wc2022.repositories

import dev.mfazio.wc2022.services.PartyService
import dev.mfazio.wc2022.types.domain.Party
import dev.mfazio.wc2022.types.domain.Player

object PartyRepository {

    fun getPartyByCode(partyCode: String): Party? =
        PartyService.getPartyByCode(partyCode)

    fun getPartiesForUser(userId: String?): List<Party> =
        userId?.let {
            PartyService.getPartiesForUser(userId)
        } ?: emptyList()

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

        // TODO: What if this call fails?
        PartyService.savePartyToFirebase(party)

        PartyService.addPlayerToParty(
            partyCode = party.code,
            player = owner
        )

        return PartyService.getPartyByCode(party.code)
    }

    fun updatePartyName(code: String, name: String, ownerId: String): Party? {
        val party = PartyService.getPartyByCode(code)

        if (party?.owner?.id != ownerId) {
            return null
        }

        PartyService.updatePartyName(code, name)

        return PartyService.getPartyByCode(code)
    }

    fun deleteParty(code: String, ownerId: String): Boolean = PartyService.getPartyByCode(code)?.let { party ->
        party.owner.id != ownerId && PartyService.deleteParty(partyCode = code)
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

        addPlayerToParty(party.code, Player(id = ownerId, name = ownerName))

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
        val party = PartyService.getPartyByCode(partyCode)
        println("AddPlayerToParty.party: $party")
        party
    } else null

    fun removePlayerFromParty(partyCode: String, playerId: String): Party? {
        PartyService.removePlayerFromParty(partyCode, playerId)

        //TODO: get the updated party, probably manually done.
        return null
    }

    // Here comes the fun part.
    fun distributeTeamsForParty(): Party? {
        return null
    }

    //TODO: Check for duplicated party tokens
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

suspend fun main() {
    println("go")
}
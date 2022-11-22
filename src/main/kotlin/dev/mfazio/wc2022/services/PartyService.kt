package dev.mfazio.wc2022.services

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dev.mfazio.wc2022.types.db.PartyDbModel
import dev.mfazio.wc2022.types.db.PlayerDbModel
import dev.mfazio.wc2022.types.domain.Party
import dev.mfazio.wc2022.types.domain.Player
import dev.mfazio.wc2022.types.domain.PlayerWithTeams
import dev.mfazio.wc2022.types.external.randomperson.ExternalRandomPersonResult

object PartyService : ApiService() {

    private const val basePartyPath = "/parties"
    private const val randomPersonUrl = "https://randomuser.me/api?results={count}"

    private val partyRef = FirebaseAdmin.db.getReference(basePartyPath)

    private var parties: List<PartyDbModel> = emptyList()

    init {
        partyRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot?) {
                snapshot?.children?.toList()?.let { dbParties ->
                    parties = dbParties.map {
                        it.getValue(PartyDbModel::class.java)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError?) {
                println("Parties read failed: ${error?.code}")
            }
        })
    }

    fun getPartyByCode(partyCode: String, includeDeleted: Boolean = false) =
        parties.filter { includeDeleted || it.isDeleted != true }.firstOrNull {
            it.code == partyCode
        }?.toParty()

    fun getPartiesForUser(userId: String, includeDeleted: Boolean = false) = parties
        .filter { (includeDeleted || it.isDeleted != true) && it.players.containsKey(userId) }
        .mapNotNull { it.toParty() }

    fun savePartyToFirebase(party: Party): Party? = try {
        FirebaseAdmin.db
            .getReference("$basePartyPath/${party.code}")
            .setValueAsync(PartyDbModel.fromParty(party))
            .get()

        party
    } catch (e: Exception) {
        null
    }

    fun addPlayerToParty(partyCode: String, player: Player): Party? = try {
        FirebaseAdmin.db
            .getReference("$basePartyPath/$partyCode/players/${player.id}")
            .setValueAsync(PlayerDbModel.fromPlayer(player))
            .get()

        getPartyByCode(partyCode)
    } catch (e: Exception) {
        null
    }

    fun updatePartyName(partyCode: String, partyName: String): Party? = try {
        FirebaseAdmin.db
            .getReference("$basePartyPath/$partyCode/name")
            .setValueAsync(partyName)
            .get()

        getPartyByCode(partyCode)
    } catch (e: Exception) {
        null
    }

    fun removePlayerFromParty(partyCode: String, playerId: String): Party? = try {
        FirebaseAdmin.db
            .getReference("$basePartyPath/$partyCode/players/${playerId}")
            .removeValueAsync()
            .get()

        getPartyByCode(partyCode)
    } catch (e: Exception) {
        null
    }

    fun deleteParty(partyCode: String): Boolean = try {
        FirebaseAdmin.db
            .getReference("$basePartyPath/$partyCode/isDeleted")
            ?.setValueAsync(true)
            ?.get()

        true
    } catch (e: Exception) {
        false
    }

    fun updateTeamsForParty(partyCode: String, teams: List<PlayerWithTeams>) = try {
        teams.forEach { (player, teams) ->
            val dbTeamMap = teams.associate { it.teamId to it.teamName }
            FirebaseAdmin.db
                .getReference("$basePartyPath/$partyCode/players/${player.id}/teams")
                ?.setValueAsync(dbTeamMap)
                ?.get()

            getPartyByCode(partyCode)
        }

        getPartyByCode(partyCode)
    } catch (e: Exception) {
        null
    }

    fun isCodeInUse(partyCode: String) = parties.any { it.code == partyCode }

    suspend fun getRandomPlayers(count: Int = 1): List<Player> {
        val randomPeople =
            getResultOrNull<ExternalRandomPersonResult>(randomPersonUrl.replace("{count}", count.toString()))

        return randomPeople?.results?.map { externalRandomPerson ->
            Player(
                id = externalRandomPerson.login.md5,
                name = externalRandomPerson.name.fullName
            )
        } ?: emptyList()
    }
}
package dev.mfazio.wc2022.services

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dev.mfazio.wc2022.types.db.PartyDbModel
import dev.mfazio.wc2022.types.db.PlayerDbModel
import dev.mfazio.wc2022.types.domain.Party
import dev.mfazio.wc2022.types.domain.Player
import dev.mfazio.wc2022.types.external.randomperson.ExternalRandomPersonResult

object PartyService : ApiService() {

    private const val basePartyPath = "/parties"
    private const val randomPersonUrl = "https://randomuser.me/api?results={count}"

    private val partyRef = FirebaseAdmin.db.getReference(basePartyPath)

    private var parties: List<PartyDbModel> = emptyList()

    init {
        partyRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                parties = snapshot.children.toList().map {
                    it.getValue(PartyDbModel::class.java)
                }
            }

            override fun onCancelled(error: DatabaseError?) {
                println("Read failed: ${error?.code}")
            }
        })
    }

    fun getPartyByCode(partyCode: String, includeDeleted: Boolean = false) = parties.filter { includeDeleted || !it.isDeleted }.firstOrNull {
        it.code == partyCode
    }?.toParty()

    fun getPartiesForUser(userId: String) = parties
        .filter { it.players.containsKey(userId) }
        .mapNotNull { it.toParty() }

    fun savePartyToFirebase(party: Party) {
        FirebaseAdmin.db
            .getReference("$basePartyPath/${party.code}")
            .setValueAsync(PartyDbModel.fromParty(party))
    }

    fun addPlayerToParty(partyCode: String, player: Player) {
        FirebaseAdmin.db
            .getReference("$basePartyPath/$partyCode/players/${player.id}")
            .setValueAsync(PlayerDbModel.fromPlayer(player))
    }

    fun updatePartyName(partyCode: String, partyName: String) {
        FirebaseAdmin.db
            .getReference("$basePartyPath/$partyCode/name")
            .setValueAsync(partyName)
    }

    fun removePlayerFromParty(partyCode: String, playerId: String) {
        FirebaseAdmin.db
            .getReference("$basePartyPath/$partyCode/players/${playerId}")
            .removeValueAsync()
    }

    fun deleteParty(partyCode: String): Boolean {
        FirebaseAdmin.db.getReference("$basePartyPath/$partyCode/isDeleted")?.setValueAsync(true)
        //TODO: Can I check if this fails?
        return true
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

data class TestPartyDbModel(
    val name: String = "",
    val code: String = "",
)
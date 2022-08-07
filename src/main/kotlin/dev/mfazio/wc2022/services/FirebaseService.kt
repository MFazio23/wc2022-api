package dev.mfazio.wc2022.services

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.FirebaseDatabase
import dev.mfazio.utils.extensions.getResourceAsStream
import dev.mfazio.wc2022.URLs
import dev.mfazio.wc2022.types.RankedTeam
import dev.mfazio.wc2022.types.db.RankedTeamDbModel

object FirebaseService {
    private const val firebaseAuthFileName = "wc2022-7a7c9-firebase-adminsdk-o3wd8-b4829c8828.json"
    private val firebaseAuthFileStream = FirebaseService::class.getResourceAsStream(firebaseAuthFileName)
    private val firebaseOptions = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(firebaseAuthFileStream))
        .setDatabaseUrl(URLs.dbUrl)
        .build()

    private val db: FirebaseDatabase

    init {
        FirebaseApp.initializeApp(firebaseOptions)

        db = FirebaseDatabase.getInstance()
    }

    suspend fun getRankingsFromFirebase() = db.getReference("/rankings")

    fun saveRankingsToFirebase(rankedTeams: List<RankedTeam>) {
        db.getReference("/rankings").setValueAsync(rankedTeams.associate(RankedTeamDbModel.Companion::fromRankedTeam))
    }
}
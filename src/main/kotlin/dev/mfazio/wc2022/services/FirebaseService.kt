package dev.mfazio.wc2022.services

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.*
import dev.mfazio.utils.extensions.getResourceAsStream
import dev.mfazio.wc2022.URLs

object FirebaseService {
    //TODO: Move this to an env variable or something.
    private const val firebaseAuthFileName = "wc2022-7a7c9-firebase-adminsdk-o3wd8-b65d3f7269.json"
    private val firebaseAuthFileStream = FirebaseService::class.getResourceAsStream(firebaseAuthFileName)
    private val firebaseOptions = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(firebaseAuthFileStream))
        .setDatabaseUrl(URLs.dbUrl)
        .build()

    val db: FirebaseDatabase

    init {
        FirebaseApp.initializeApp(firebaseOptions)

        db = FirebaseDatabase.getInstance()
    }

    fun getJsonUrlFromPath(path: String) = "${URLs.dbUrl}$path.json"

}
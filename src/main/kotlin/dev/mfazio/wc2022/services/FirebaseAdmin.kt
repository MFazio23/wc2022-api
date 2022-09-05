package dev.mfazio.wc2022.services

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.*
import dev.mfazio.utils.extensions.getResourceAsStream
import dev.mfazio.wc2022.URLs

object FirebaseAdmin {
    //TODO: Move this to an env variable or something.
    private const val firebaseAuthFileName = "wc2022-7a7c9-firebase-adminsdk-o3wd8-b4829c8828.json"
    private val firebaseAuthFileStream = FirebaseAdmin::class.getResourceAsStream(firebaseAuthFileName)
    private val firebaseOptions = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(firebaseAuthFileStream))
        .setDatabaseUrl(URLs.dbUrl)
        .build()

    val app: FirebaseApp = FirebaseApp.initializeApp(firebaseOptions)
    val db: FirebaseDatabase = FirebaseDatabase.getInstance(app)

    fun init() {
        // This initializes the Firebase instance so we can use it later.
        FirebaseApp.getInstance()
    }

    fun getJsonUrlFromPath(path: String) = "${URLs.dbUrl}$path.json"
}
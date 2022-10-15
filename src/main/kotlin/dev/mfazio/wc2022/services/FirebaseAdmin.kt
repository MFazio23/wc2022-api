package dev.mfazio.wc2022.services

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.*
import de.sharpmind.ktor.EnvConfig
import dev.mfazio.utils.extensions.getResourceAsStream
import dev.mfazio.wc2022.URLs

object FirebaseAdmin {
    private val firebaseAuthFileName = EnvConfig.getString("firebaseAuthFile")
    private val firebaseAuthFileStream = FirebaseAdmin::class.getResourceAsStream(firebaseAuthFileName)
    private val firebaseOptions = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(firebaseAuthFileStream))
        .setDatabaseUrl(URLs.dbUrl)
        .build()

    private val app: FirebaseApp = FirebaseApp.initializeApp(firebaseOptions)
    val db: FirebaseDatabase = FirebaseDatabase.getInstance(app)

    fun getJsonUrlFromPath(path: String) = "${URLs.dbUrl}$path.json"
}
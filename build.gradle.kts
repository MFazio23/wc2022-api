val fazioUtilsVersion: String by project
val firebaseVersion: String by project
val ktorVersion: String by project
val ktorEnvConfigVersion: String by project
val kotlinVersion: String by project
val kotlinxCoroutinesVersion: String by project
val logbackVersion: String by project

plugins {
    application
    id("war")
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.10"
    id("org.gretty") version "3.0.6"
}

group = "dev.mfazio.wc2022"
version = "0.0.1"
application {
    mainClass.set("dev.mfazio.wc2022.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    google()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("com.google.firebase:firebase-admin:$firebaseVersion")
    implementation("com.github.MFazio23:fazio-utils-jvm:$fazioUtilsVersion")
    implementation("de.sharpmind.ktor:ktor-env-config:$ktorEnvConfigVersion")
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-server-auth-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-locations-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-compression-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-cors-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-metrics-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-servlet:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-server-tomcat-jvm:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$kotlinxCoroutinesVersion")
}

gretty {
    servletContainer = "tomcat9"
    contextPath = "/"
    logbackConfigFile = "src/main/resources/logback.xml"
}

afterEvaluate {
    tasks.getByName("run") {
        dependsOn("appRun")
    }
}
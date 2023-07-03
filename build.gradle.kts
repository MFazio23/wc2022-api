import io.ktor.plugin.features.*

val fazioUtilsVersion: String by project
val firebaseVersion: String by project
val ktorVersion: String by project
val ktorEnvConfigVersion: String by project
val kotlinVersion: String by project
val kotlinxCoroutinesVersion: String by project
val logbackVersion: String by project

plugins {
    kotlin("jvm") version "1.8.22"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.22"
    id("io.ktor.plugin") version "2.3.2"
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

    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-core-jvm:2.1.2")
    implementation("io.ktor:ktor-client-cio-jvm:2.1.2")

    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation("io.ktor:ktor-server-auth-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-cio:$ktorVersion")
    implementation("io.ktor:ktor-server-compression-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-cors-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-metrics-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-resources-jvm:$ktorVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$kotlinxCoroutinesVersion")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}

ktor {
    docker {
        jreVersion.set(JreVersion.JRE_17)
        localImageName.set("wwc2023-api")
        imageTag.set("0.0.1-preview")
        portMappings.set(
            listOf(
                DockerPortMapping(
                    8023,
                    8023,
                    DockerPortMappingProtocol.TCP
                )
            )
        )
    }
}


afterEvaluate {
    tasks.getByName("run") {
        dependsOn("appRun")
    }
}
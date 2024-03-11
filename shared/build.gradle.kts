plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.23"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.0")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.compose.material3)
            implementation(libs.androidx.activity.compose)
            implementation("androidx.datastore:datastore-preferences:1.0.0")
            implementation("io.ktor:ktor-client-okhttp:1.6.4")
            implementation("io.ktor:ktor-client-json:1.6.4")
            implementation("io.ktor:ktor-client-json-jvm:1.6.4")
            implementation("io.ktor:ktor-client-serialization:1.6.4")



        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.example.appkmm"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
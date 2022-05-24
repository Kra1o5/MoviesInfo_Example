plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
    id ("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.randomdroids.moviesinfo"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))

    Libs.androidLibs.forEach { androidLibs ->
        implementation(androidLibs)
    }

    Libs.androidKaptLibs.forEach { androidKaptLibs ->
        kapt(androidKaptLibs)
    }

    Libs.libs.forEach { libs ->
        implementation(libs)
    }

    Libs.kotlinLibs.forEach { kotlinLibs ->
        implementation(kotlinLibs)
    }

    Libs.testLibs.forEach { testLibs ->
        testImplementation(testLibs)
    }

    Libs.androidTestLibs.forEach { androidTestLibs ->
        androidTestImplementation(androidTestLibs)
    }

    Libs.debugLibs.forEach { debugLibs ->
       debugImplementation(debugLibs)
    }
}
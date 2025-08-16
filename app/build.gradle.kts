plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    kotlin("kapt")
}

android {
    namespace = "com.example.jikananimeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.jikananimeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("Boolean", "HIDE_POSTERS", "false")
        }
        release {
            buildConfigField("Boolean", "HIDE_POSTERS", "true")
            isMinifyEnabled = false
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Hilt dependency for dependency injection
    implementation(libs.hilt.android)
    // Hilt compiler for annotation processing
    kapt("com.google.dagger:hilt-compiler:2.46")

    // Retrofit library for making HTTP requests
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    // Gson converter for JSON serialization/deserialization with Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Glide library for image loading and caching
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // Glide compiler for annotation processing
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    // Glide integration for Jetpack Compose
    implementation("com.github.bumptech.glide:compose:1.0.0-alpha.1")

    // Room library for local database management
    implementation("androidx.room:room-runtime:2.5.2")
    // Room Kotlin extensions for easier database operations
    implementation("androidx.room:room-ktx:2.5.2")
    // Room compiler for annotation processing
    kapt("androidx.room:room-compiler:2.5.2")

    // Jetpack Navigation Compose for navigation in Compose applications
    implementation("androidx.navigation:navigation-compose:2.7.7")
    // ConstraintLayout for Jetpack Compose
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    // Material Design 3 components for Jetpack Compose
    implementation("androidx.compose.material3:material3:1.2.1")

    // Core UI components for Jetpack Compose
    implementation("androidx.compose.ui:ui:1.0.0")
    // Support for Jetpack Compose in Activity
    implementation("androidx.activity:activity-compose:1.3.0")
    // Coil library for image loading optimized for Jetpack Compose
    implementation("io.coil-kt:coil-compose:2.4.0")

}

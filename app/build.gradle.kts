plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.capstone.compassionly"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.capstone.compassionly"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }
    buildTypes {
        release {
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // View Model
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.activity.ktx)

    // Dot indicator
    implementation("com.tbuonomo:dotsindicator:4.3")

    // Login with Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.android.gms:play-services-auth:21.1.1")
    implementation("com.google.firebase:firebase-auth-ktx:23.0.0")
    implementation("androidx.credentials:credentials:1.2.2")
    implementation("androidx.credentials:credentials-play-services-auth:1.2.2")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.0")

    // Custom SignIn Button
    implementation(libs.custom.google.signin.button)

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

}
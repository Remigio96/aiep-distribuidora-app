plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // alias(libs.plugins.kotlin.compose) <-- Esta línea puede que ya no sea necesaria
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "cl.aiep.distribuidoraapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "cl.aiep.distribuidoraapp"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    // Como ya no usamos Compose en MainActivity, esta sección podría eliminarse,
    // pero no causa daño si se queda.
    buildFeatures {
        compose = false
    }
}

dependencies {
    // --- LIBRERÍAS PRINCIPALES ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // --- FIREBASE ---
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database.ktx)

    // --- GOOGLE PLAY SERVICES ---
    implementation(libs.play.services.location)

    // --- LIBRERÍAS DE TESTING ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
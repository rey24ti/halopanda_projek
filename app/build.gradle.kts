plugins {
    // Panggil langsung, jangan pakai alias(libs...) agar tidak error
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.rey_halopanda"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rey_halopanda"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true // WAJIB AKTIF untuk ViewBinding
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
}

dependencies {
    // === CORE & UI ===
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0") // ✅ Hapus duplikat 1.11.0
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(libs.androidx.activity.ktx)

    // === VIEWPAGER2 & FRAGMENT (untuk TabLayout) ===
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("androidx.fragment:fragment-ktx:1.8.2")

    // === RECYCLERVIEW (untuk Tab C - Material) ===
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // === CARDVIEW (untuk Card Item) ===
    implementation("androidx.cardview:cardview:1.0.0")

    // === GLIDE (untuk load gambar dari URL) ===
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // === TESTING ===
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
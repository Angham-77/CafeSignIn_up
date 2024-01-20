plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.cafemobileaplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cafemobileaplication"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("at.favre.lib:bcrypt:0.9.0")
    // for adding recyclerview
    implementation ("androidx.recyclerview:recyclerview:1.2.0")

    // for adding cardview
    implementation ("androidx.cardview:cardview:1.0.0")
    //test

    testImplementation ("org.mockito:mockito-core:3.12.4")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    testImplementation ("com.google.truth:truth:1.1.3")
    testImplementation ("org.robolectric:robolectric:4.7.1")
    implementation ("androidx.drawerlayout:drawerlayout:1.1.1")





}
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.oppowatch.haptics"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.oppowatch.haptics"
        minSdk = 28
        targetSdk = 28
        versionCode = 10000
        versionName = "1.0.0"
    }

    signingConfigs {
        create("release") {
            storeFile = file("${rootDir}/keystore/vibration.jks")
            storePassword = "geminiwearos"
            keyAlias = "vibrationkey"
            keyPassword = "geminiwearos"
            enableV1Signing = true
            enableV2Signing = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
}

import io.davidosemwota.major.buildsource.Libs
import io.davidosemwota.major.buildsource.MajorAppConfig

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = MajorAppConfig.SdkVersions.compileSdk

    defaultConfig {
        applicationId = MajorAppConfig.applicationId
        minSdk = MajorAppConfig.SdkVersions.minimumSdk
        targetSdk = MajorAppConfig.SdkVersions.targetSdk
        versionCode = MajorAppConfig.versionCode
        versionName = MajorAppConfig.versionName

        testInstrumentationRunner = MajorAppConfig.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests.all { test ->
            test.useJUnitPlatform()
            test.testLogging {
                events("passed","skipped", "failed")
            }
        }
    }
}

dependencies {
    implementation(project(MajorAppConfig.Modules.presentation))

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation(Libs.Compose.junit4)
    debugImplementation(Libs.Compose.uiTooling)
}
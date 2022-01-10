
import io.davidosemwota.major.buildsource.Libs
import io.davidosemwota.major.buildsource.MajorAppConfig
import io.davidosemwota.major.buildsource.extentions.addUnitTestsDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = MajorAppConfig.SdkVersions.compileSdk

    defaultConfig {
        minSdk = MajorAppConfig.SdkVersions.minimumSdk
        targetSdk = MajorAppConfig.SdkVersions.targetSdk
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

    //Retrofit
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.retrofitgson)
    implementation(Libs.httpLogging)
    implementation(Libs.AndroidX.annotation)
    //Timber
    api(Libs.timber)
    //Coroutines
    api(Libs.Coroutines.core)
    api(Libs.Coroutines.android)

    addUnitTestsDependencies()
}
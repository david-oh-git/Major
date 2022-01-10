package io.davidosemwota.major.buildsource

object MajorAppConfig {

    const val applicationId = "io.davidosemwota.major"
    const val versionCode = 1
    const val versionName = "0.0.1"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    object Modules {
        const val storage = ":storage"
        const val domain = ":domain"
        const val presentation = ":presentation"
    }

    object SdkVersions {
        const val compileSdk = 31
        const val minimumSdk = 21
        const val targetSdk = 31
    }
}
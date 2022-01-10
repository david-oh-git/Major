
import io.davidosemwota.major.buildsource.Libs
import io.davidosemwota.major.buildsource.MajorAppConfig

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = MajorAppConfig.SdkVersions.compileSdk

    defaultConfig {
        minSdk = MajorAppConfig.SdkVersions.minimumSdk
        targetSdk = MajorAppConfig.SdkVersions.targetSdk
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.version
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

    implementation(project(MajorAppConfig.Modules.domain))

    api(Libs.AndroidX.coreKtx)
    api(Libs.Compose.ui)
    api(Libs.Compose.material)
    api(Libs.Compose.toolingPreview)
    api(Libs.AndroidX.Lifecycle.runtime)
    api(Libs.AndroidX.Lifecycle.viewmodel)
    api(Libs.AndroidX.Lifecycle.viewModelCompose)
    api(Libs.Compose.activity)

    kapt(Libs.AndroidX.Lifecycle.compiler)

}
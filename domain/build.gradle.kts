
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

    implementation(project(MajorAppConfig.Modules.storage))

    implementation("androidx.core:core-ktx:1.7.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    addUnitTestsDependencies()
}
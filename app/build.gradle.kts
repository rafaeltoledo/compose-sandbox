plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.plugin.compose)
  alias(libs.plugins.kotlin.plugin.serialization)
  alias(libs.plugins.lint.detekt)
  alias(libs.plugins.lint.spotless)
}

android {
  compileSdk = libs.versions.android.sdk.compile.get().toInt()
  namespace = "net.rafaeltoledo.sandbox"

  defaultConfig {
    applicationId = "net.rafaeltoledo.sandbox"
    minSdk = libs.versions.android.sdk.min.get().toInt()
    targetSdk = libs.versions.android.sdk.target.get().toInt()
    versionCode = 1
    versionName = "0.0.1"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  signingConfigs {
    getByName("debug") {
      storeFile = File("$rootDir/distribution/debug.keystore")
    }
  }

  buildTypes {
    release {
      signingConfig = signingConfigs.getByName("debug")
      isMinifyEnabled = true
      isShrinkResources = true

      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
}

kotlin {
  jvmToolchain(17)
}

spotless {
  kotlin {
    ktlint(libs.versions.ktlint.get())
  }
}

dependencies {
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.serialization.json)

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.activity.compose)

  implementation(platform(libs.androidx.compose.bom))

  implementation(libs.androidx.lifecycle.viewmodel.compose)

  implementation(libs.androidx.navigation.compose)

  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui.tooling.preview)

  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.test.manifest)

  implementation(libs.retrofit)
  implementation(libs.retrofit.kotlinx.serialization)
  implementation(libs.okhttp)
  implementation(libs.okhttp.logging)
  implementation(libs.conscrypt)

  implementation(libs.coil.compose)
  implementation(libs.coil.network.okhttp)

  testImplementation(libs.test.junit)

  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.androidx.test.espresso)

  lintChecks(libs.lint.slack.compose)
}

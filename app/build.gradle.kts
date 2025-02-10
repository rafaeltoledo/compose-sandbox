plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.lint.detekt)
  alias(libs.plugins.lint.spotless)
}

android {
  compileSdk = 34
  namespace = "net.rafaeltoledo.sandbox"

  defaultConfig {
    applicationId = "net.rafaeltoledo.sandbox"
    minSdk = 24
    targetSdk = 34
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

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.11"
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

  implementation(libs.google.material)

  implementation(libs.retrofit)
  implementation(libs.retrofit.moshi)
  implementation(libs.moshi.kotlin)
  implementation(libs.okhttp)
  implementation(libs.okhttp.logging)
  implementation(libs.conscrypt)

  implementation(libs.coil.compose)

  implementation(libs.accompanist.navigation.animation)
  implementation(libs.accompanist.insets.ui)

  testImplementation(libs.test.junit)

  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.androidx.test.espresso)

  lintChecks(libs.lint.slack.compose)
}

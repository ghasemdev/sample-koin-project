@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)

  alias(libs.plugins.ksp)
  alias(libs.plugins.kotlin.serialization)
  id(libs.plugins.kotlin.parcelize.get().pluginId)
}

android {
  namespace = "com.parsuomash.samplekoinproject"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.parsuomash.samplekoinproject"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      isShrinkResources = true
      signingConfig = signingConfigs.getByName("debug")
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
    create("profileable") {
      isDebuggable = false
      signingConfig = signingConfigs.getByName("debug")
    }
  }
  compileOptions {
    isCoreLibraryDesugaringEnabled = true

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
    freeCompilerArgs = listOf(
      "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
      "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
    )
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
//  implementation(project(":SDK"))
  implementation("ir.parsoumash.com:sdk:1.0.0")

  // kotlin
  implementation(libs.bundles.kotlinx)
  // Need to target Android devices running below API 26 for kotlin date time
  coreLibraryDesugaring(libs.desugar.jdk.libs)

  // Android
  implementation(libs.bundles.androidx)
  implementation(libs.bundles.lifcycle)

  // DI
  implementation(libs.bundles.koin)
  ksp(libs.koin.ksp.compiler)

  // Compose
  implementation(libs.bundles.compose)

  // Test
  testImplementation(libs.junit)
  // Android Test
  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.espresso.core)
  androidTestImplementation(libs.ui.test.junit4)

  // Debug Libs
  debugImplementation(libs.ui.tooling)
  debugImplementation(libs.ui.test.manifest)
  debugImplementation(libs.leakcanary.android)

  // Logger
  implementation(libs.timber)
}

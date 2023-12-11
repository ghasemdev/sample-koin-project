@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)

  alias(libs.plugins.ksp)
  id("maven-publish")
}

android {
  namespace = "com.parsuomash.sdk"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
      "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
      "-opt-in=org.koin.core.annotation.KoinExperimentalAPI"
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
  kotlin {
    sourceSets.main {
      kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
      kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
  }
}

ksp {
  arg("KOIN_CONFIG_CHECK", "true")
}

dependencies {
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

afterEvaluate {
  publishing {
    publications {
      create<MavenPublication>("release") {
        from(components.getByName("release"))
        groupId = "ir.parsoumash.com"
        artifactId = "sdk"
        version = "1.0.0"
      }
    }

    repositories {
      maven {
        url = uri("http://asdfghjkl")
        credentials {
          username = "maven.username"
          password = "maven.password"
        }
      }
    }
  }
}

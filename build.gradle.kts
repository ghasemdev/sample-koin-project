import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.ksp) apply false
}

subprojects {
  tasks.withType(KotlinCompile::class.java).configureEach {
    kotlinOptions {
      if (project.findProperty("composeCompilerReports") == "true") {
        freeCompilerArgs = listOf(
          "-P",
          "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
              project.layout.buildDirectory.asFile.get().absolutePath + "/compose_compiler"
        )
      }
      if (project.findProperty("composeCompilerMetrics") == "true") {
        freeCompilerArgs = listOf(
          "-P",
          "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
              project.layout.buildDirectory.asFile.get().absolutePath + "/compose_compiler"
        )
      }
    }
  }
}

tasks.register("testAll") {
  dependsOn("test", "connectedAndroidTest")
  group = "affogato"
  description = "Run all tests"
}

tasks.register("clean", Delete::class) {
  delete(rootProject.layout.buildDirectory)
}

true // Needed to make the Suppress annotation work for the plugins block

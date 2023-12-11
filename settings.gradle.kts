pluginManagement {
  repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenLocal()
    google()
    mavenCentral()
  }
}

rootProject.name = "SampleKoinProject"
include(":app")
include(":SDK")

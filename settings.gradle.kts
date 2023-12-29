pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CaloryTracker"
include(":app")
include(":core")
include(":onboarding")
include(":tracker")
include(":onboarding:domain")
include(":onboarding:presentation")
include(":tracker:domain")
include(":tracker:data")
include(":tracker:presentation")
include(":core-ui")

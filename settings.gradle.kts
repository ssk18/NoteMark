pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

rootProject.name = "Notemark"
include(":app")
include(":core")
include(":core:domain")
include(":core:data")
include(":core:presentation")
include(":core:presentation:designsystem")
include(":core:presentation:ui")
include(":auth")
include(":auth:data")
include(":auth:presentation")
include(":auth:domain")

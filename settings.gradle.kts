pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    val hmiRepoPath = extra["atom.hmiRepoPath"] ?: ""
    includeBuild("$hmiRepoPath/manifests/common")
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    val hmiRepoPath = getEnvPropertyByName("HMI_REPO_PATH", extra["atom.hmiRepoPath"] ?: "")
    versionCatalogs {
        create("libs") {
            from(files("$hmiRepoPath/manifests/common/libs.versions.toml"))
        }
    }
}

fun getEnvPropertyByName(propName: String, defaultValue: Any): Any {
    val result = System.getenv(propName)
    return result ?: defaultValue
}

rootProject.name = "NavigationBar"
include(":app")
 
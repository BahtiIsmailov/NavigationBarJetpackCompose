import com.android.build.gradle.AppExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
}
true // Needed to make the Suppress annotation work for the plugins block

val hmiRepoPath = extra["atom.hmiRepoPath"] ?: ""

//For platform signing purposes
subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("com.android.application")) {
            project.extensions.configure(AppExtension::class) {
                val config = signingConfigs.maybeCreate("debug")
                config.apply {
                    storeFile = file("../../.repo/manifests/common/keystore/platform.jks")
                    storePassword = "android"
                    keyAlias = "android"
                    keyPassword = "android"
                }
                buildTypes.configureEach {
                    signingConfig = config
                    project.logger.info("Applied config for $this")
                }
            }
        }
    }
}
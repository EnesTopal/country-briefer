// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.7.6"
        val gradle_plugin_version= "1.9.21"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$gradle_plugin_version")


    }
}

plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("org.jetbrains.kotlin.jvm") version "1.9.21" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
}
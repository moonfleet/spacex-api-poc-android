buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.39.1")
        classpath("com.google.gms:google-services:4.3.3")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("com.moonfleet.spacex.app")
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.moonfleet.spacex.HiltTestRunner"
    }
    buildTypes {
        getByName("release") {}
        getByName("debug") {}
        flavorDimensions += "environment"
        productFlavors {
            create("prod") {
                dimension = "environment"
            }
        }
    }
}

dependencies {
    implementation(project(AppDependencies.featureLaunchRegistry))
    implementation(project(AppDependencies.libCoreUi))
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.9.1")
}

allprojects {
    afterEvaluate {
        configurations.findByName("androidTestImplementation")?.run {
            exclude(group = "io.mockk", module = "mockk-agent-jvm")
        }
    }
}
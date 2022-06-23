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
    id("com.moonfleet.spacex.feature")
}

android {
    buildTypes {
        getByName("release") {}
        getByName("debug") {}
        flavorDimensions += "environment"
        productFlavors {
            create("prod") {
                dimension = "environment"
                buildConfigField("String", "HOST_URL", "\"https://api.spacexdata.com\"")
            }
        }
    }

}

dependencies {
    implementation(project(AppDependencies.libUtils))
    implementation(project(AppDependencies.libCoreUi))
    implementation(project(AppDependencies.libCleanMvi))
    implementation(project(AppDependencies.libCoreRestful))
}
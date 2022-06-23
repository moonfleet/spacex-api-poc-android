plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    kotlin("jvm") version "1.3.72"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.android.tools.build:gradle:7.0.2")
    implementation(gradleApi())
    implementation(localGroovy())
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

gradlePlugin {
    plugins {
        create("com.moonfleet.spacex.app") {
            id = "com.moonfleet.spacex.app"
            implementationClass = "SpaceXAppPlugin"
        }
        create("com.moonfleet.spacex.lib") {
            id = "com.moonfleet.spacex.lib"
            implementationClass = "SpaceXLibPlugin"
        }
        create("com.moonfleet.spacex.feature") {
            id = "com.moonfleet.spacex.feature"
            implementationClass = "SpaceXFeaturePlugin"
        }
        create("com.moonfleet.spacex.androidlib") {
            id = "com.moonfleet.spacex.androidlib"
            implementationClass = "SpaceXAndroidLibPlugin"
        }
    }
}
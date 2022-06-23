import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType

fun DependencyHandlerScope.include(configurationName: String, libraries: ArrayList<String>) {
    libraries.forEach {
        add(configurationName, it)
    }
}

fun Project.configureAndroid(): Project {
    extensions.getByType<BaseExtension>().run {
        compileSdkVersion(AppConfig.compileSdk)

        defaultConfig {
            minSdk = AppConfig.minSdk
            targetSdk = AppConfig.targetSdk
            versionCode = AppConfig.versionCode
            versionName = AppConfig.versionName
            buildToolsVersion(AppConfig.buildToolsVersion)
            testInstrumentationRunner = AppConfig.hiltTestInstrumentation
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }

            getByName("debug") {
                isTestCoverageEnabled = true
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        buildFeatures.viewBinding = true
        dataBinding {
            isEnabled = true
        }

    }
    return this
}
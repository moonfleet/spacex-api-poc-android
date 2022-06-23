import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class SpaceXAppPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project
            .applyAppPlugins()
            .configureAndroid()
            .configureAppDependencies()
    }
}

private fun Project.applyAppPlugins(): Project {
    plugins.apply("com.android.application")
    plugins.apply("dagger.hilt.android.plugin")
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-kapt")
    return this
}

private fun Project.configureAppDependencies(): Project {
    dependencies {
        include( "implementation", AppDependencies.appLibraries)
        include( "kapt", AppDependencies.kaptLibraries)
        include( "annotationProcessor", AppDependencies.annotationProcessorLibraries)
        include("testImplementation", AppDependencies.testLibraries)
        include("androidTestImplementation", AppDependencies.androidTestLibraries)
        include("kaptAndroidTest", AppDependencies.kaptAndroidLibraries)
    }
    return this
}
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

open class SpaceXAndroidLibPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project
            .applyAndroidLibPlugins()
            .configureAndroid()
            .configureAndroidLibDependencies()
    }
}

private fun Project.applyAndroidLibPlugins(): Project {
    plugins.apply("com.android.library")
    plugins.apply("dagger.hilt.android.plugin")
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-kapt")
    plugins.apply("kotlin-parcelize")
    return this
}

private fun Project.configureAndroidLibDependencies(): Project {
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
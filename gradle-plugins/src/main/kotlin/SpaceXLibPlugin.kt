import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class SpaceXLibPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project
            .applyLibPlugins()
            .configureLibJava()
            .configureLibDependencies()
    }
}

private fun Project.configureLibJava(): Project {
    extensions.getByType<JavaPluginExtension>().run {
        sourceCompatibility = JavaVersion.VERSION_1_7
        targetCompatibility = JavaVersion.VERSION_1_7
    }
    return this
}


private fun Project.applyLibPlugins(): Project {
    plugins.apply("java-library")
    plugins.apply("kotlin")
    plugins.apply("kotlin-kapt")
    return this
}

private fun Project.configureLibDependencies(): Project {
    dependencies {
        include( "implementation", AppDependencies.libLibraries)
        include( "kapt", AppDependencies.kaptLibraries)
        include("testImplementation", AppDependencies.testLibraries)
    }
    return this
}
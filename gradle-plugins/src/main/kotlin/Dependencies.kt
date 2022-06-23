import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    // Generic
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val kotlinStdLibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val featureLaunchRegistry = ":features:launch-registry"
    val libCleanMvi = ":libs:clean-mvi"
    val libCoreUi = ":libs:core-ui"
    val libUtils = ":libs:utils"
    val libCoreRestful = ":libs:core-restful"

    // Android UI
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    private val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragmentKtx}"
    private val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigationUiKtx}"
    private val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    private val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    private val lifecycleCommonJava = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleKtx}"
    private val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    private val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    private val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    private val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    private val material = "com.google.android.material:material:${Versions.material}"
    private val okHttpUrlConnection = "com.squareup.okhttp3:okhttp-urlconnection:4.4.1"
    private val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    private val gson = "com.google.code.gson:gson:${Versions.gson}"
    private val retrofit2Mock = "com.squareup.retrofit2:retrofit-mock:2.6.0"
    private val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha02"
    private val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    private val idlingResource = "androidx.test.espresso:espresso-idling-resource:${Versions.idlingResource}"

    // Hilt
    private val hiltCore = "com.google.dagger:hilt-core:${Versions.hilt}"
    private val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    private val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    private val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    private val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // Test
    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val testCore = "androidx.test:core:${Versions.testCore}"
    private val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archCoreTesting}"
    private val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutineTest}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espressoIntents}"
    private val testCoreKtx = "androidx.test:core-ktx:${Versions.testCoreKtx}"
    private val testCoreJunitKtx = "androidx.test.ext:junit-ktx:${Versions.testCoreJunitKtx}"
    private val hiltTest = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    private val junitRunner = "androidx.test:runner:1.4.0"
    private val espressoContrib = "androidx.test.espresso:espresso-contrib:3.3.0"
    private val testRules = "androidx.test:rules:1.4.0"
    private val mockk = "io.mockk:mockk:${Versions.mockk}"
    private val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    private val mockkAgent = "io.mockk:mockk-agent-jvm:${Versions.mockk}"
    private val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(hiltAndroid)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(recyclerView)
        add(navigationFragmentKtx)
        add(navigationUiKtx)
        add(coroutinesCore)
        add(coroutinesAndroid)
        add(lifecycleCommonJava)
        add(lifecycleViewModel)
        add(timber)
        add(loggingInterceptor)
        add(activityKtx)
        add(material)
        add(okHttpUrlConnection)
        add(retrofit2)
        add(retrofit2ConverterGson)
        add(gson)
        add(retrofit2Mock)
        add(lifecycleRuntime)
        add(glide)
        add(idlingResource)
    }

    val libLibraries = arrayListOf<String>().apply {
        add(kotlinStdLibJdk7)
        add(hiltCore)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(hiltCompiler)
    }

    val kaptAndroidLibraries = arrayListOf<String>().apply {
        add(hiltAndroidCompiler)
    }

    val annotationProcessorLibraries = arrayListOf<String>().apply {
        add(glideCompiler)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(testCore)
        add(espressoCore)
        add(espressoIntents)
        add(testCoreKtx)
        add(testCoreJunitKtx)
        add(hiltTest)
        add(junitRunner)
        add(espressoContrib)
        add(testRules)
        add(mockkAndroid)
        add(mockkAgent)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
        add(mockk)
        add(mockitoCore)
        add(archCoreTesting)
        add(coroutineTest)
    }

}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}
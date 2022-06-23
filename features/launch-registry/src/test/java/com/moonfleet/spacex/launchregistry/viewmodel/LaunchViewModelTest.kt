package com.moonfleet.spacex.launchregistry.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchUIModel
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchesEffect
import com.moonfleet.spacex.feature.launchregistry.ui.viewmodel.LaunchesViewModel
import com.moonfleet.spacex.feature.launchregistry.usecase.*
import com.moonfleet.spacex.launchregistry.utils.TestUtils.WEBCAST_URL
import com.moonfleet.spacex.launchregistry.utils.TestUtils.WIKI_URL
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@DelicateCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LaunchViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private var application = mockk<Application>()
    private var fetchCompanyInfoUseCase = mockk<FetchCompanyInfoUseCase>()
    private var fetchAllLaunchesUseCase = mockk<FetchAllLaunchesUseCase>()
    private var fetchRocketUseCase = mockk<FetchRocketUseCase>()
    private var formatCompanyInfoUseCase = mockk<FormatCompanyInfoUseCase>()
    private var convertLaunchUseCase = mockk<ConvertLaunchUseCase>()
    private var buildLaunchFilterUseCase = mockk<BuildLaunchFilterUseCase>()
    private var filterLaunchesUseCase = mockk<FilterLaunchesUseCase>()

    private val mockLaunchUIModel = mockk<LaunchUIModel>(relaxed = true) {
        every { webcast } returns WEBCAST_URL
        every { wikipedia } returns WIKI_URL
        every { article } returns null
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `test ViewModel effect`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val viewModel = LaunchesViewModel(
                application,
                fetchCompanyInfoUseCase,
                fetchAllLaunchesUseCase,
                fetchRocketUseCase,
                formatCompanyInfoUseCase,
                convertLaunchUseCase,
                buildLaunchFilterUseCase,
                filterLaunchesUseCase
            )

            viewModel.onLaunchClick(mockLaunchUIModel)
            val effect = viewModel.effectFlow.first() as LaunchesEffect.BottomSheet
            assertEquals(WEBCAST_URL, effect.links.videoUrl)
            assertEquals(WIKI_URL, effect.links.wikiUrl)
            assertNull(WIKI_URL, effect.links.articleUrl)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

}
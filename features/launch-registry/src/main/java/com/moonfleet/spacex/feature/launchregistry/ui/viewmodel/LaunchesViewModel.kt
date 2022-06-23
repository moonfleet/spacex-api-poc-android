package com.moonfleet.spacex.feature.launchregistry.ui.viewmodel

import android.app.Application
import com.moonfleet.spacex.cleanmvi.BaseViewModel
import com.moonfleet.spacex.feature.launchregistry.R
import com.moonfleet.spacex.feature.launchregistry.ui.model.*
import com.moonfleet.spacex.feature.launchregistry.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val app: Application,
    private val fetchCompanyInfoUseCase: FetchCompanyInfoUseCase,
    private val fetchAllLaunchesUseCase: FetchAllLaunchesUseCase,
    private val fetchRocketUseCase: FetchRocketUseCase,
    private val formatCompanyInfoUseCase: FormatCompanyInfoUseCase,
    private val convertLaunchUseCase: ConvertLaunchUseCase,
    private val buildLaunchFilterUseCase: BuildLaunchFilterUseCase,
    private val filterLaunchesUseCase: FilterLaunchesUseCase
) : BaseViewModel<LaunchesState, LaunchesAction, LaunchesEffect>(
    LaunchesState.Loading,
    { error -> LaunchesState.Error(error) }) {

    val companyInfoData = MutableStateFlow("")
    val launchFilter = MutableStateFlow<LaunchFilter?>(null)
    val emptyResultSetViewGone = MutableStateFlow(false)

    override fun onAction(state: LaunchesState, action: LaunchesAction) {
        when (action) {
            is LaunchesAction.OnScreenLoaded -> {
                onScreenLoaded()
            }
            is LaunchesAction.OnBackButton -> {}
        }
    }

    private fun onScreenLoaded() {
        if (state.value is LaunchesState.Loaded) {
            emitState(state.value)
        } else {
            fetchAllLaunches()
        }
    }

    private fun fetchAllLaunches() {
        emitState(LaunchesState.Loading)
        launch {
            val companyInfo = fetchCompanyInfoUseCase()
            Timber.e("companyInfo fetched")
            companyInfoData.value = formatCompanyInfoUseCase(app.resources.getString(R.string.company_info_template), companyInfo)
            Timber.e("companyInfo pushed")
            val launchesFiltered = filterLaunchesUseCase(fetchAllLaunchesUseCase(), launchFilter.value)
            Timber.e("launches filtered")
            if(launchFilter.value == null) {
                launchFilter.value = buildLaunchFilterUseCase(launchesFiltered)
                Timber.e("launch filter built")
            }
            val launchUiModels = launchesFiltered.map {
                val rocket = fetchRocketUseCase(it.rocket)
                convertLaunchUseCase(
                    it,
                    rocket,
                    app.resources.getString(R.string.rocket_template),
                    app.resources.getString(R.string.days_diff_title_since),
                    app.resources.getString(R.string.days_diff_title_from)
                )
            }
            emptyResultSetViewGone.value = launchUiModels.isNotEmpty()
            emitState(LaunchesState.Loaded(companyInfoData.value, launchUiModels))
        }
    }

    fun onLaunchClick(launch: LaunchUIModel) {
        emitEffect(
            LaunchesEffect.BottomSheet(
                Links(
                    articleUrl = launch.article,
                    wikiUrl = launch.wikipedia,
                    videoUrl = launch.webcast
                )
            )
        )
    }

    fun updateFilter(newYears: MutableList<LaunchYear>? = null, newAscendingOrder: Boolean? = null, newSuccessStatus: Boolean?) {
        val oldFilter = launchFilter.value
        launchFilter.value =
            LaunchFilter(newYears?.associate { it.year to it.selected } ?: (oldFilter?.years
                ?: mapOf()),
                newSuccessStatus,
                newAscendingOrder ?: (oldFilter?.ascendingOrder ?: true))
        fetchAllLaunches()
    }

    fun onActionFilterClick() {
        emitEffect(LaunchesEffect.Filter)
    }

    companion object {
        const val KEY_WEBCAST = "webcast"
        const val KEY_ARTICLE = "article"
        const val KEY_WIKIPEDIA = "wikipedia"
    }

}
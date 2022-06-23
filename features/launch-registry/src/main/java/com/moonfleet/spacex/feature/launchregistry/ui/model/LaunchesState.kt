package com.moonfleet.spacex.feature.launchregistry.ui.model

import com.moonfleet.spacex.cleanmvi.State

sealed class LaunchesState: State {
    object Loading : LaunchesState()
    data class Loaded(val companyInfo: String, val launches: List<LaunchUIModel>) : LaunchesState()
    data class Error(val error: String) : LaunchesState()
}
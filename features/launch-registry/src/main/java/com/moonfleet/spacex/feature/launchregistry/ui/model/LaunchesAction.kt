package com.moonfleet.spacex.feature.launchregistry.ui.model

import com.moonfleet.spacex.cleanmvi.Action

sealed class LaunchesAction : Action {
    object OnScreenLoaded : LaunchesAction()
    object OnBackButton : LaunchesAction()
}
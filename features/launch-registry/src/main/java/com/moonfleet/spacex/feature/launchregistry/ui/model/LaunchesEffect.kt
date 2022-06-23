package com.moonfleet.spacex.feature.launchregistry.ui.model

import com.moonfleet.spacex.cleanmvi.Effect

sealed class LaunchesEffect : Effect {
    data class BottomSheet(val links: Links) : LaunchesEffect()
    object Filter : LaunchesEffect()
}
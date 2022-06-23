package com.moonfleet.spacex.feature.launchregistry.ui.model

data class LaunchUIModel(
    val patchImageUrl: String?,
    val mission: String,
    val timestamp: String,
    val rocket: String,
    val daysDiffTitle: String,
    val daysDiff: Int,
    val isSuccessful: Boolean,
    val webcast: String? = null,
    val article: String? = null,
    val wikipedia: String? = null
)
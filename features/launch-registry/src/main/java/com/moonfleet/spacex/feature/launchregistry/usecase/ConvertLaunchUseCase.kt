package com.moonfleet.spacex.feature.launchregistry.usecase

import com.moonfleet.spacex.feature.launchregistry.model.Launch
import com.moonfleet.spacex.feature.launchregistry.model.Rocket
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchUIModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.abs

class ConvertLaunchUseCase @Inject constructor(val dateFormat: SimpleDateFormat) {
    operator fun invoke(
        launch: Launch,
        rocket: Rocket,
        rocketTemplate: String,
        daysDiffTitleSince: String,
        daysDiffTitleFrom: String
    ) = with(launch) {
        val daysDiff =
            ((System.currentTimeMillis() / 1000).toInt() - launch.dateUnix) / 60 / 60 / 24
        val timestampFormatted = dateFormat.format(Date(launch.dateUnix * 1000L))

        LaunchUIModel(
            links.patch.small,
            name,
            timestampFormatted,
            String.format(rocketTemplate, rocket.name, rocket.type),
            if(daysDiff < 0) daysDiffTitleFrom else daysDiffTitleSince,
            abs(daysDiff),
            success,
            links.webcast,
            links.article,
            links.wikipedia
        )
    }
}
package com.moonfleet.spacex.feature.launchregistry.usecase

import com.moonfleet.spacex.feature.launchregistry.model.Launch
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchFilter
import com.moonfleet.spacex.feature.launchregistry.year
import javax.inject.Inject

class BuildLaunchFilterUseCase @Inject constructor() {

    operator fun invoke(launches: List<Launch>): LaunchFilter {
        val allYears = launches.groupBy { it.year() }.mapValues { true }
        return LaunchFilter(allYears)
    }

}
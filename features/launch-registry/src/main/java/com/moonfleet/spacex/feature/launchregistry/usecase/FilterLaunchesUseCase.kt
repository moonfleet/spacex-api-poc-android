package com.moonfleet.spacex.feature.launchregistry.usecase

import com.moonfleet.spacex.feature.launchregistry.model.Launch
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchFilter
import com.moonfleet.spacex.feature.launchregistry.year
import javax.inject.Inject

class FilterLaunchesUseCase @Inject constructor() {

    operator fun invoke(launches: List<Launch>, launchFilter: LaunchFilter?): List<Launch> {
        if(launchFilter == null) return launches
        val launchesFiltered = launches.filter {
            (launchFilter.years[it.year()] ?: false) && (launchFilter.successStatus?.let { filter -> filter == it.success } ?: true)
        }
        return if(launchFilter.ascendingOrder) {
            launchesFiltered.sortedBy { it.dateUnix }
        } else {
            launchesFiltered.sortedByDescending { it.dateUnix }
        }
    }

}
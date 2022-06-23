package com.moonfleet.spacex.feature.launchregistry

import android.os.Parcelable
import com.moonfleet.spacex.feature.launchregistry.model.Launch
import com.moonfleet.spacex.feature.launchregistry.ui.model.LaunchYear
import java.util.*

fun Launch.year(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = dateUnix * 1000L
    return calendar.get(Calendar.YEAR)
}

fun Array<Parcelable>?.asYearsMap(): Map<Int, Boolean> {
    if (this == null) return mapOf()
    return Arrays.copyOf(this, size, Array<LaunchYear>::class.java)
        .associate { year -> year.year to year.selected }
}

fun Map<Int, Boolean>?.toYearsList(): MutableList<LaunchYear> =
    if(this == null) mutableListOf() else map { entry -> LaunchYear(entry.key, entry.value) }.toMutableList()
package com.moonfleet.spacex.feature.launchregistry.ui.model

/**
 * Represents a filter for launches.
 * @property years A map of years as keys, and values as Booleans to indicate if the year should be
 * included into the result set
 * @property successStatus true to show Successful launches only, false to show Unsuccessful ones only,
 * null for Show All
 * @property ascendingOrder true to show in ascending order by launch timestamp starting with earliest,
 * false for descending order
 */
data class LaunchFilter(
    val years: Map<Int, Boolean>,
    val successStatus: Boolean? = null,
    val ascendingOrder: Boolean = true
)
package com.moonfleet.spacex.feature.launchregistry.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchYear(val year: Int, var selected: Boolean) : Parcelable
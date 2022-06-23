package com.moonfleet.spacex.feature.launchregistry.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Links (val articleUrl: String?, val wikiUrl: String?, val videoUrl: String?) : Parcelable
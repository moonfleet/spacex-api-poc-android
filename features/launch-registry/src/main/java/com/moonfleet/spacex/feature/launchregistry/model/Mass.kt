package com.moonfleet.spacex.feature.launchregistry.model


import com.google.gson.annotations.SerializedName

data class Mass(
    @SerializedName("kg")
    val kg: Int,
    @SerializedName("lb")
    val lb: Int
)
package com.moonfleet.spacex.feature.launchregistry.model


import com.google.gson.annotations.SerializedName

data class LandingLegs(
    @SerializedName("material")
    val material: Any,
    @SerializedName("number")
    val number: Int
)
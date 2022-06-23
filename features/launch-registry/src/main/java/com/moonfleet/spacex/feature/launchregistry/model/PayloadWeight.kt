package com.moonfleet.spacex.feature.launchregistry.model


import com.google.gson.annotations.SerializedName

data class PayloadWeight(
    @SerializedName("id")
    val id: String,
    @SerializedName("kg")
    val kg: Int,
    @SerializedName("lb")
    val lb: Int,
    @SerializedName("name")
    val name: String
)
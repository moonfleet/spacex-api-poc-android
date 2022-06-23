package com.moonfleet.spacex.feature.launchregistry.service

import com.moonfleet.spacex.corerestful.ServiceGenerator
import com.moonfleet.spacex.feature.launchregistry.BuildConfig

object SpaceXServiceGenerator : ServiceGenerator<SpaceXService>() {
    override fun getBaseURL() = BuildConfig.HOST_URL

    val spaceXService
        get() = getService(SpaceXService::class.java)

}
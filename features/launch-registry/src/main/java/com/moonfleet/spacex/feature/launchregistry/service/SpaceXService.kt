package com.moonfleet.spacex.feature.launchregistry.service

import com.moonfleet.spacex.feature.launchregistry.model.CompanyInfo
import com.moonfleet.spacex.feature.launchregistry.model.Launch
import com.moonfleet.spacex.feature.launchregistry.model.Rocket
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXService {

    @GET("v4/company")
    suspend fun fetchCompanyInfo(): CompanyInfo

    @GET("v5/launches")
    suspend fun fetchAllLaunches(): List<Launch>

    @GET("v4/rockets/{rocketId}")
    suspend fun fetchRocket(@Path("rocketId") rocketId: String): Rocket

}
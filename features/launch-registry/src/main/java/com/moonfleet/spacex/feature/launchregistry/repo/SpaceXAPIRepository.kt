package com.moonfleet.spacex.feature.launchregistry.repo

import com.moonfleet.spacex.feature.launchregistry.model.CompanyInfo
import com.moonfleet.spacex.feature.launchregistry.model.Launch
import com.moonfleet.spacex.feature.launchregistry.model.Rocket

interface SpaceXAPIRepository {

    suspend fun fetchCompanyInfo(): CompanyInfo
    suspend fun fetchAllLaunches(): List<Launch>
    suspend fun fetchRocket(id: String): Rocket

}
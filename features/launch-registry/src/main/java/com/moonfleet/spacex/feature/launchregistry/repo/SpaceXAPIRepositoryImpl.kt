package com.moonfleet.spacex.feature.launchregistry.repo

import com.moonfleet.spacex.feature.launchregistry.model.CompanyInfo
import com.moonfleet.spacex.feature.launchregistry.model.Launch
import com.moonfleet.spacex.feature.launchregistry.model.Rocket
import com.moonfleet.spacex.feature.launchregistry.service.SpaceXService
import javax.inject.Inject

class SpaceXAPIRepositoryImpl @Inject constructor(val service: SpaceXService) : SpaceXAPIRepository {

    private var companyInfoCache: CompanyInfo? = null
    private var launchesCache: List<Launch>? = null
    private var rocketsCache: HashMap<String, Rocket> = HashMap()

    override suspend fun fetchCompanyInfo(): CompanyInfo =
        companyInfoCache ?: service.fetchCompanyInfo().also { companyInfoCache = it }

    override suspend fun fetchAllLaunches(): List<Launch> =
        launchesCache ?: service.fetchAllLaunches().also { launchesCache = it }

    override suspend fun fetchRocket(id: String): Rocket =
        rocketsCache[id] ?: service.fetchRocket(id).also { rocketsCache[id] = it }
}
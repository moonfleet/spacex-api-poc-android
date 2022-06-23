package com.moonfleet.spacex.repo

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.moonfleet.spacex.feature.launchregistry.model.CompanyInfo
import com.moonfleet.spacex.feature.launchregistry.model.Launch
import com.moonfleet.spacex.feature.launchregistry.model.Rocket
import com.moonfleet.spacex.feature.launchregistry.repo.SpaceXAPIRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class TestSpaceXRepositoryImpl @Inject constructor(val app: Application) : SpaceXAPIRepository {
    override suspend fun fetchCompanyInfo(): CompanyInfo {
        return GSON.fromJson(assetFileToString(COMPANY_INFO_JSON), CompanyInfo::class.java)
    }

    override suspend fun fetchAllLaunches(): List<Launch> {
        return listOf(
            GSON.fromJson(
                assetFileToString(LAUNCHES_JSON),
                Launch::class.java
            )
        )
    }

    override suspend fun fetchRocket(id: String): Rocket {
        delay(2000)
        return GSON.fromJson(assetFileToString(ROCKETS_JSON), Rocket::class.java)
    }

    companion object {
        val GSON = Gson()
        val LAUNCHES_JSON = "json/launches.json"
        val ROCKETS_JSON = "json/rockets.json"
        val COMPANY_INFO_JSON = "json/company_info.json"

        fun assetFileToString(filename: String) =
            InstrumentationRegistry.getInstrumentation().context.assets.open(filename)
                .bufferedReader().use { it.readText() }
    }
}
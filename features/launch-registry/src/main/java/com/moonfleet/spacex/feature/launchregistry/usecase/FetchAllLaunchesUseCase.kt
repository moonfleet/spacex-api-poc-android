package com.moonfleet.spacex.feature.launchregistry.usecase

import com.moonfleet.spacex.feature.launchregistry.model.Launch
import com.moonfleet.spacex.feature.launchregistry.repo.SpaceXAPIRepository
import javax.inject.Inject

class FetchAllLaunchesUseCase @Inject constructor(private val apiRepository: SpaceXAPIRepository) {

    suspend operator fun invoke(): List<Launch> = apiRepository.fetchAllLaunches()

}
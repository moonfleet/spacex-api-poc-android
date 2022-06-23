package com.moonfleet.spacex.feature.launchregistry.usecase

import com.moonfleet.spacex.feature.launchregistry.model.Rocket
import com.moonfleet.spacex.feature.launchregistry.repo.SpaceXAPIRepository
import javax.inject.Inject

class FetchRocketUseCase @Inject constructor(private val apiRepository: SpaceXAPIRepository) {

    suspend operator fun invoke(rocketId: String): Rocket = apiRepository.fetchRocket(rocketId)

}
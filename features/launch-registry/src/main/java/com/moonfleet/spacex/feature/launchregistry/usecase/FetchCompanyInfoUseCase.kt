package com.moonfleet.spacex.feature.launchregistry.usecase

import com.moonfleet.spacex.feature.launchregistry.model.CompanyInfo
import com.moonfleet.spacex.feature.launchregistry.repo.SpaceXAPIRepository
import javax.inject.Inject

class FetchCompanyInfoUseCase @Inject constructor(private val apiRepository: SpaceXAPIRepository) {

    suspend operator fun invoke(): CompanyInfo = apiRepository.fetchCompanyInfo()

}
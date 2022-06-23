package com.moonfleet.spacex.feature.launchregistry.usecase

import com.moonfleet.spacex.feature.launchregistry.model.CompanyInfo
import javax.inject.Inject

class FormatCompanyInfoUseCase @Inject constructor() {
    operator fun invoke(template: String, companyInfo: CompanyInfo) = with(companyInfo) {
        String.format(template, name, founder, founded, employees, launchSites, valuation)
    }
}
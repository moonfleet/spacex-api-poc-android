package com.moonfleet.spacex.coreui.navigation

import android.app.Application
import android.content.Intent
import android.os.Bundle
import javax.inject.Inject

class Navigator @Inject constructor(private val app: Application) {
    fun launchRegistryIntent() = actionIntent("com.moonfleet.spacex.feature.launchregistry.entrypoint")

    fun actionIntent(action: String) = Intent(action).setPackage(app.packageName)
    fun actionIntent(action: String, extras: Bundle) = actionIntent(action).putExtras(extras)
}
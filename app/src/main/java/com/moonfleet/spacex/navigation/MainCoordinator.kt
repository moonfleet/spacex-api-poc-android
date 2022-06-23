package com.moonfleet.spacex.navigation

import com.moonfleet.spacex.coreui.base.BaseActivity
import com.moonfleet.spacex.coreui.navigation.Coordinator
import com.moonfleet.spacex.coreui.navigation.Navigator
import javax.inject.Inject

class MainCoordinator @Inject constructor(private val navigator: Navigator) : Coordinator() {

    fun fromMain(activity: BaseActivity) {
        openAndFinish(activity, navigator.launchRegistryIntent())
    }

}
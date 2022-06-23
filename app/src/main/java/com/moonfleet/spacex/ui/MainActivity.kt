package com.moonfleet.spacex.ui

import android.os.Bundle
import com.moonfleet.spacex.coreui.base.BaseActivity
import com.moonfleet.spacex.navigation.MainCoordinator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var coordinator: MainCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coordinator.fromMain(this)
    }
}
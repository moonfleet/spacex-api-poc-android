package com.moonfleet.spacex.di

import android.app.Application
import com.moonfleet.spacex.coreui.navigation.Navigator
import com.moonfleet.spacex.navigation.MainCoordinator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoordinatorModule {

    @Provides
    fun provideMainCoordinator(navigator: Navigator): MainCoordinator = MainCoordinator(navigator)

}
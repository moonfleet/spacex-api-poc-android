package com.moonfleet.spacex.coreui.di

import android.app.Application
import com.moonfleet.spacex.coreui.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Provides
    fun provideNavigator(application: Application) = Navigator(application)

}
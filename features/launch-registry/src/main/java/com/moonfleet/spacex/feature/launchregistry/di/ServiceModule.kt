package com.moonfleet.spacex.feature.launchregistry.di

import com.moonfleet.spacex.feature.launchregistry.service.SpaceXServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Provides
    internal fun provideSpaceXService() = SpaceXServiceGenerator.spaceXService
}
package com.moonfleet.spacex.feature.launchregistry.di

import com.moonfleet.spacex.feature.launchregistry.repo.SpaceXAPIRepository
import com.moonfleet.spacex.feature.launchregistry.repo.SpaceXAPIRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(apiRepositoryImpl: SpaceXAPIRepositoryImpl) : SpaceXAPIRepository

}
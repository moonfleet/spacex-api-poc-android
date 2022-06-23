package com.moonfleet.spacex.di

import com.moonfleet.spacex.repo.TestSpaceXRepositoryImpl
import com.moonfleet.spacex.feature.launchregistry.di.RepositoryModule
import com.moonfleet.spacex.feature.launchregistry.repo.SpaceXAPIRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class TestRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(apiRepositoryImpl: TestSpaceXRepositoryImpl) : SpaceXAPIRepository
}
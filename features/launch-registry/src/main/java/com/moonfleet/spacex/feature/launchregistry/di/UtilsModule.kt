package com.moonfleet.spacex.feature.launchregistry.di

import android.app.Application
import com.moonfleet.spacex.feature.launchregistry.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.SimpleDateFormat
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {
    @Provides
    internal fun provideDateFormat(app: Application) =
        SimpleDateFormat(app.resources.getString(R.string.date_format), Locale.getDefault())
}
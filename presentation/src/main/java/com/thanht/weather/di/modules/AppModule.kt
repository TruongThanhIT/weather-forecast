package com.thanht.weather.di.modules

import com.thanht.data.net.ApiConnection
import com.thanht.data.util.provideService
import com.thanht.data.weather.WeatherListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[InstallIn(SingletonComponent::class) Module]
object AppModule {

    @[Provides Singleton]
    internal fun provideWeatherListService(apiConnection: ApiConnection): WeatherListService {
        return apiConnection.provideService()
    }
}
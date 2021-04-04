package com.thanht.weather.di.modules

import com.thanht.data.weather.WeatherRepositoryImpl
import com.thanht.domain.city.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[InstallIn(SingletonComponent::class) Module]
abstract class UserModule {

    // repository
    @Binds
    abstract fun WeatherRepositoryImpl.bindWeatherRepository(): WeatherRepository
}
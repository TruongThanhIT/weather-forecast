package com.thanht.weather.di.modules


import com.thanht.data.net.ApiConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[InstallIn(SingletonComponent::class) Module]
class NetworkModule {
    @[Provides Singleton]
    internal fun provideApiConnection(): ApiConnection {
        return ApiConnection()
    }
}

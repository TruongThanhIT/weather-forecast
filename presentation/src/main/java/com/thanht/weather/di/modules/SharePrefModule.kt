package com.thanht.weather.di.modules

import android.content.Context
import com.thanht.data.cache.AppSharePref
import com.thanht.data.cache.AppSharePrefImpl
import com.thanht.data.cache.CoreSharedPref
import com.thanht.data.cache.SharePrefFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[InstallIn(SingletonComponent::class) Module]
class SharePrefModule {

    @[Provides Singleton]
    fun provideCoreSharePref(@ApplicationContext context: Context) = SharePrefFactory.create(context)

    @[Provides Singleton]
    fun provideAppSharePref(coreSharedPref: CoreSharedPref): AppSharePref =
        AppSharePrefImpl(coreSharedPref)

}

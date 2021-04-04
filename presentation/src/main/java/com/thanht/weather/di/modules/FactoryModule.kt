package com.thanht.weather.di.modules

import androidx.lifecycle.ViewModelProvider
import com.thanht.weather.di.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface FactoryModule {

    @get:Binds
    val ViewModelFactory.viewModelFactory: ViewModelProvider.Factory
}

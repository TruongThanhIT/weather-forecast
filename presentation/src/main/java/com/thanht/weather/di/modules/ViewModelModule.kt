package com.thanht.weather.di.modules

import androidx.lifecycle.ViewModel
import com.thanht.weather.MainViewModel
import com.thanht.weather.di.mapkeys.ViewModelKey
import com.thanht.weather.home.list.HomeViewModel
import com.thanht.weather.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@[InstallIn(SingletonComponent::class) Module]
interface ViewModelModule {
    @get:[Binds IntoMap ViewModelKey(HomeViewModel::class)]
    val HomeViewModel.homeViewModel: ViewModel

    @get:[Binds IntoMap ViewModelKey(SplashViewModel::class)]
    val SplashViewModel.splashViewModel: ViewModel

//    @get:[Binds IntoMap ViewModelKey(MainViewModel::class)]
//    val MainViewModel.mainViewModel: ViewModel
}
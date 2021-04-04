package com.thanht.weather.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanht.data.executor.PostExecutionThread
import com.thanht.weather.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashViewModel @Inject constructor(
) : BaseViewModel() {

    private val mSplashState = MutableLiveData<SplashState>()
    var splashState: LiveData<SplashState> = mSplashState

    fun updateState(state: SplashState) {
        mSplashState.postValue(state)
    }
}

sealed class SplashState {
    object HomeActivity : SplashState()
}
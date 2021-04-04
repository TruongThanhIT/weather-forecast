package com.thanht.weather.home.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.thanht.data.cache.AppSharePref
import com.thanht.data.executor.PostExecutionThread
import com.thanht.domain.base.BaseResult
import com.thanht.domain.city.WeatherRepository
import com.thanht.domain.model.WeatherModel
import com.thanht.weather.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val DEBOUNCE_TIME = 500L
const val FORECAST_DAY = 7
const val UNITS = "metric"
const val MIN_SEARCH_LENGTH = 3

class HomeViewModel @Inject constructor(
        private val mWeatherRepository: WeatherRepository,
        private val mSharePref: AppSharePref,
        mPostExecutionThread: PostExecutionThread
) : BaseViewModel() {

    private val mQueryStringLive = MutableStateFlow("")
    var queryStringLive = mQueryStringLive

    val activeGetWeatherButtonLive: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(queryStringLive.asLiveData()) { query ->
            value = query.length >= MIN_SEARCH_LENGTH
        }
    }

    val currentSearchList: HashMap<String, List<WeatherModel>> = hashMapOf()

    fun queryWeather(query: CharSequence?) {
        val iQuery = query?.toString()?.trim().orEmpty()
        if (iQuery == mQueryStringLive.value) return
        mQueryStringLive.value = iQuery
    }

    @ExperimentalCoroutinesApi
    val weatherForecastResultLive = mQueryStringLive
            .debounce(DEBOUNCE_TIME)
            .flatMapLatest {
                if (it.isNotEmpty() && it.length >= MIN_SEARCH_LENGTH) {
                    if (currentSearchList.isNotEmpty() && currentSearchList.containsKey(it)) {
                        flowOf(BaseResult.Success(0, "", currentSearchList[queryStringLive.value]))
                    } else {
                        mWeatherRepository.getListWeather(it, FORECAST_DAY, mSharePref.appId, UNITS)
                    }
                } else {
                    emptyFlow()
                }
            }
            .flowOn(mPostExecutionThread.io)
            .catch {
                it.printStackTrace()
            }
            .asLiveData(viewModelScope.coroutineContext)
}
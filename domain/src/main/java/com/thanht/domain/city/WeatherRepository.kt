package com.thanht.domain.city

import com.thanht.domain.base.BaseResult
import com.thanht.domain.model.WeatherModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getListWeather(
        cityName: String,
        forecastDay: Int,
        appId: String,
        units: String
    ): Flow<BaseResult<List<WeatherModel>>>
}
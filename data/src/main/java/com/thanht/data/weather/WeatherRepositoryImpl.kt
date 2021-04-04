package com.thanht.data.weather

import com.thanht.data.executor.PostExecutionThread
import com.thanht.data.util.flowRes
import com.thanht.data.util.toWeatherModels
import com.thanht.domain.base.BaseResponse
import com.thanht.domain.city.WeatherRepository
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val mWeatherListService: WeatherListService,
    private val mPostExecutionThread: PostExecutionThread
) : WeatherRepository {

    override fun getListWeather(
        cityName: String,
        forecastDay: Int,
        appId: String,
        units: String
    ) = flowRes(mPostExecutionThread) {
        val response = mWeatherListService.getWeatherForecast(
            cityName, forecastDay, appId, units
        )
        BaseResponse(
            response.cod.orEmpty().toInt(),
            response.messageNum?.toString().orEmpty(),
            "",
            response.listWeatherForecast?.toWeatherModels() ?: emptyList()
        )
    }.flowOn(mPostExecutionThread.io)
}
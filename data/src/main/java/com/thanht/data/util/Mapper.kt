package com.thanht.data.util

import com.thanht.data.model.WeatherResponse
import com.thanht.domain.model.WeatherModel


fun WeatherResponse.WeatherForecast.toWeatherModel(): WeatherModel = WeatherModel(
    date = dt ?: 0L,
    averageTemp = temp?.run { min?.plus(max ?: 0.0)?.div(2.0) } ?: 0.0,
    pressure = pressure ?: 0,
    humidity = humidity ?: 0,
    desc = weather?.firstOrNull()?.description.orEmpty()
)

fun List<WeatherResponse.WeatherForecast?>.toWeatherModels(): List<WeatherModel> =
    this.map { it?.toWeatherModel() ?: WeatherModel(0L, 0.0, 0, 0, "") }
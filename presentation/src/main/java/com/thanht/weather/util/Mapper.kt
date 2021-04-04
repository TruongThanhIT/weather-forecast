package com.thanht.weather.util

import com.thanht.domain.model.WeatherModel
import com.thanht.weather.model.WeatherInfo

fun WeatherModel.toWeatherInfo(): WeatherInfo =
    WeatherInfo(date, averageTemp, pressure, humidity, desc)

fun List<WeatherModel>.toWeatherInfoList(): List<WeatherInfo> = map { it.toWeatherInfo() }
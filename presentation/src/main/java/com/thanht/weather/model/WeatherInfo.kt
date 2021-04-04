package com.thanht.weather.model

data class WeatherInfo(
    val date: Long,
    val averageTemp: Double,
    val pressure: Int,
    val humidity: Int,
    val desc: String
)
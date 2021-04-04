package com.thanht.data.weather

import com.thanht.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherListService {
    @GET("2.5/forecast/daily")
    suspend fun getWeatherForecast(
        @Query("q") cityName: String,
        @Query("cnt") forecastDay: Int,
        @Query("appid") appId: String,
        @Query("units") unit: String
    ): WeatherResponse
}
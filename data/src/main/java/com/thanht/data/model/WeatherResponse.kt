package com.thanht.data.model


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val listWeatherForecast: List<WeatherForecast?>?,
    @SerializedName("message")
    val messageNum: Double?
) {
    data class City(
        @SerializedName("coord")
        val coord: Coord?,
        @SerializedName("country")
        val country: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("population")
        val population: Int?,
        @SerializedName("timezone")
        val timezone: Int?
    ) {
        data class Coord(
            @SerializedName("lat")
            val lat: Double?,
            @SerializedName("lon")
            val lon: Double?
        )
    }

    data class WeatherForecast(
        @SerializedName("clouds")
        val clouds: Int?,
        @SerializedName("deg")
        val deg: Int?,
        @SerializedName("dt")
        val dt: Long?,
        @SerializedName("feels_like")
        val feelsLike: FeelsLike?,
        @SerializedName("humidity")
        val humidity: Int?,
        @SerializedName("pop")
        val pop: Float?,
        @SerializedName("pressure")
        val pressure: Int?,
        @SerializedName("rain")
        val rain: Double?,
        @SerializedName("speed")
        val speed: Double?,
        @SerializedName("sunrise")
        val sunrise: Int?,
        @SerializedName("sunset")
        val sunset: Int?,
        @SerializedName("temp")
        val temp: Temp?,
        @SerializedName("weather")
        val weather: List<Weather?>?
    ) {
        data class FeelsLike(
            @SerializedName("day")
            val day: Double?,
            @SerializedName("eve")
            val eve: Double?,
            @SerializedName("morn")
            val morn: Double?,
            @SerializedName("night")
            val night: Double?
        )

        data class Temp(
            @SerializedName("day")
            val day: Double?,
            @SerializedName("eve")
            val eve: Double?,
            @SerializedName("max")
            val max: Double?,
            @SerializedName("min")
            val min: Double?,
            @SerializedName("morn")
            val morn: Double?,
            @SerializedName("night")
            val night: Double?
        )

        data class Weather(
            @SerializedName("description")
            val description: String?,
            @SerializedName("icon")
            val icon: String?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("main")
            val main: String?
        )
    }
}
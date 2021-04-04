package com.thanht.weather.home.list

import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.buildSpannedString
import androidx.databinding.BindingAdapter
import com.thanht.weather.R
import com.thanht.weather.model.WeatherInfo
import com.thanht.weather.util.DateTimeUtils.formatDate
import kotlin.math.roundToInt

@BindingAdapter("app:weather")
fun AppCompatTextView.formatWeatherInfo(weatherInfo: WeatherInfo?) {
    weatherInfo ?: return
    text = buildSpannedString {
        append(
            "${context.getString(R.string.date)}: ${weatherInfo.date.formatDate()}\n"
                    + "${context.getString(R.string.average_temp)}: ${weatherInfo.averageTemp.roundToInt()}\u2103\n"
                    + "${context.getString(R.string.pressure)}: ${weatherInfo.pressure}\n"
                    + "${context.getString(R.string.humidity)}: ${weatherInfo.humidity}%\n"
                    + "${context.getString(R.string.description)}: ${weatherInfo.desc}"
        )
    }
}
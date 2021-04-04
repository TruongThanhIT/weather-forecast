package com.thanht.weather.home.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.thanht.weather.databinding.ItemWeatherBinding
import com.thanht.weather.model.WeatherInfo

class WeatherListHolder(private val binding: ItemWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: WeatherInfo) {
        binding.apply {
            weatherInfo = data
            executePendingBindings()
        }
    }
}
package com.thanht.weather.home.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.thanht.weather.databinding.ItemWeatherBinding
import com.thanht.weather.model.WeatherInfo

class WeatherListAdapter : ListAdapter<WeatherInfo, WeatherListHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherListHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherListHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeatherInfo>() {
            override fun areItemsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}

package com.demo.weatherinfo.ui.current

import android.view.LayoutInflater
import android.view.ViewGroup
import com.demo.weatherinfo.base.BaseAdapter
import com.demo.weatherinfo.data.model.ListItem
import com.demo.weatherinfo.databinding.CityWeatherItemBinding


/**
 * Created by Rashida on 4/4/20.
 *
 */

class CurrentWeatherAdapter : BaseAdapter<ListItem, CurrentWeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentWeatherViewHolder {
        return CurrentWeatherViewHolder(
            CityWeatherItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CurrentWeatherViewHolder, position: Int) {
        list?.let {
            holder.bindData(it.get(position))
        }
    }
}
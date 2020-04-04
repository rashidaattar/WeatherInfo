package com.demo.weatherinfo.ui.forecast

import com.demo.weatherinfo.base.BaseViewHolder
import com.demo.weatherinfo.data.model.ListItem
import com.demo.weatherinfo.databinding.CityWeatherItemBinding
import com.demo.weatherinfo.databinding.ViewDateTemperatureWindBinding


/**
 * Created by Rashida on 4/4/20.
 *
 */
class ForeCastTimeViewHolder(binding: ViewDateTemperatureWindBinding) :
    BaseViewHolder<ViewDateTemperatureWindBinding>(binding) {



    fun bindData(listItem: ListItem) {
        binding.weatherData = listItem
    }
}
package com.demo.weatherinfo.ui.current

import androidx.recyclerview.widget.RecyclerView
import com.demo.weatherinfo.data.model.ListItem
import com.demo.weatherinfo.databinding.CityWeatherItemBinding


/**
 * Created by Rashida on 4/4/20.
 *
 */
class CurrentWeatherViewHolder(itemsListBinding: CityWeatherItemBinding) :
    RecyclerView.ViewHolder(itemsListBinding.getRoot()) {
    var itemsListBinding: CityWeatherItemBinding

    init {
        this.itemsListBinding = itemsListBinding
    }

    fun bindData(listItem: ListItem) {
        itemsListBinding.weatherData = listItem
    }
}
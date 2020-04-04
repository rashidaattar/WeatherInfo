package com.demo.weatherinfo.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import com.demo.weatherinfo.base.BaseAdapter
import com.demo.weatherinfo.data.model.ListItem
import com.demo.weatherinfo.databinding.ViewDateTemperatureWindBinding


/**
 * Created by Rashida on 4/4/20.
 *
 */
class ForeCastTimeAdapter : BaseAdapter<ListItem, ForeCastTimeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForeCastTimeViewHolder {
        return ForeCastTimeViewHolder(
            ViewDateTemperatureWindBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ForeCastTimeViewHolder, position: Int) {
        list?.let {
            holder.bindData(it.get(position))
        }
    }
}
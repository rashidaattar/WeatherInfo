package com.demo.weatherinfo.ui.forecast

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.weatherinfo.base.BaseAdapter
import com.demo.weatherinfo.data.model.DayTimeModel
import com.demo.weatherinfo.databinding.ForecastDaywiseListBinding


/**
 * Created by Rashida on 4/4/20.
 *
 */
class ForeCastDayAdapter(var context: Context) : BaseAdapter<DayTimeModel, ForeCastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForeCastViewHolder {
        return ForeCastViewHolder(
            ForecastDaywiseListBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {
        list?.let {
            holder.bindData(it.get(position))
            var adapter=ForeCastTimeAdapter()
            holder.binding.hourForecastList.layoutManager=LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            holder.binding.hourForecastList.adapter=adapter
            adapter.setData(it.get(position).dataList)
        }
    }

}
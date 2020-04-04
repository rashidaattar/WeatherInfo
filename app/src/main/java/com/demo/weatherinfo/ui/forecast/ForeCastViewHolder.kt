package com.demo.weatherinfo.ui.forecast

import com.demo.weatherinfo.base.BaseViewHolder
import com.demo.weatherinfo.data.model.DayTimeModel
import com.demo.weatherinfo.databinding.ForecastDaywiseListBinding


/**
 * Created by Rashida on 4/4/20.
 *
 */

class ForeCastViewHolder(binding: ForecastDaywiseListBinding) :
    BaseViewHolder<ForecastDaywiseListBinding>(binding) {

    fun bindData(data: DayTimeModel) {
        binding.data = data
    }
}
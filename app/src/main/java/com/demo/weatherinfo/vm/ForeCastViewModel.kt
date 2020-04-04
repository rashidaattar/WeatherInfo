package com.demo.weatherinfo.vm

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.weatherinfo.base.BaseViewModel
import com.demo.weatherinfo.data.model.DayTimeModel
import com.demo.weatherinfo.data.model.ForecastResponse
import com.demo.weatherinfo.data.model.ListItem
import com.demo.weatherinfo.data.remote.NetworkBoundResource
import com.demo.weatherinfo.data.remote.OpenWeatherAPI
import com.demo.weatherinfo.data.remote.Resource
import io.reactivex.Single
import java.time.format.TextStyle
import java.util.*
import javax.inject.Inject


/**
 * Created by Rashida on 4/4/20.
 *
 */
class ForeCastViewModel @Inject constructor(var weatherAPI: OpenWeatherAPI) : BaseViewModel(){

    var forecastDaywiseLiveData = MutableLiveData<List<DayTimeModel>>()

    fun loadCurrentWeatherByGeoCords(
        lat: Double,
        lon: Double,
        units: String
    ): LiveData<Resource<ForecastResponse>> {
        return object : NetworkBoundResource<ForecastResponse>() {

            override fun createCall(): Single<ForecastResponse> =
                weatherAPI.getForecastByGeoCords(lat, lon, units)

        }.asLiveData
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListForAdapter(list: List<ListItem>) {
        if (list.size > 0) {
            val items = list as List<ListItem>
            var finalList = arrayListOf<DayTimeModel>()
            var itemsListDayWise = arrayListOf<ListItem>()
            for (item in items) {
                if (items.indexOf(item) == 0)
                    itemsListDayWise.add(item)
                else {
                    if (item.getDayofWeek().equals(items.get(items.indexOf(item) - 1).getDayofWeek())) {
                        itemsListDayWise.add(item)
                    } else {
                        finalList.add(
                            DayTimeModel(
                                item.getDayofWeek().getDisplayName(
                                    TextStyle.FULL,
                                    Locale.getDefault()
                                ), itemsListDayWise
                            )
                        )
                        itemsListDayWise = arrayListOf<ListItem>()
                        itemsListDayWise.add(item)
                    }
                }

            }
            forecastDaywiseLiveData.postValue(finalList)
        }
    }
}
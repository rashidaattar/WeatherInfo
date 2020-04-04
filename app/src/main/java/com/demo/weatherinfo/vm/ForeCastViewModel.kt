package com.demo.weatherinfo.vm

import androidx.lifecycle.LiveData
import com.demo.weatherinfo.base.BaseViewModel
import com.demo.weatherinfo.data.model.ForecastResponse
import com.demo.weatherinfo.data.remote.NetworkBoundResource
import com.demo.weatherinfo.data.remote.OpenWeatherAPI
import com.demo.weatherinfo.data.remote.Resource
import io.reactivex.Single
import javax.inject.Inject


/**
 * Created by Rashida on 4/4/20.
 *
 */
class ForeCastViewModel @Inject constructor(var weatherAPI: OpenWeatherAPI) : BaseViewModel(){

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
}
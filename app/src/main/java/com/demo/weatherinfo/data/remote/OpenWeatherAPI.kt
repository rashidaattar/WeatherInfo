package com.demo.weatherinfo.data.remote

import com.demo.weatherinfo.data.model.ForecastResponse
import com.demo.weatherinfo.data.model.ListItem
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Rashida on 3/31/20.
 *
 */
interface OpenWeatherAPI {

    @GET("forecast")
    fun getForecastByGeoCords(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String
    ):Single<ForecastResponse>


    @GET("weather")
    fun getWeatherPerCity(
        @Query("q")
        city: String
    ):Observable<ListItem>

}
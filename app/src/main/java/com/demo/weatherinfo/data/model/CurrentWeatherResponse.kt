package com.demo.weatherinfo.data.model

import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(

    @SerializedName("visibility")
    val visibility: Int? = null,

    @SerializedName("timezone")
    val timezone: Int? = null,

    @SerializedName("main")
    val main: Main? = null,

    @SerializedName("clouds")
    val clouds: Clouds? = null,

    @SerializedName("sys")
    val sys: Sys? = null,

    @SerializedName("dt")
    val dt: Int? = null,

    @SerializedName("coord")
    val coord: Coord? = null,

    @SerializedName("weather")
    val weather: List<WeatherItem?>? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("cod")
    val cod: Int? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("base")
    val base: String? = null,

    @SerializedName("wind")
    val wind: Wind? = null
) 

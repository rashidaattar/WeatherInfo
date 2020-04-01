package com.demo.weatherinfo.data.model

import com.google.gson.annotations.SerializedName



data class Geoloc(

    @SerializedName( "lng")
    val lng: Double? = null,

    @SerializedName( "lat")
    val lat: Double? = null
)

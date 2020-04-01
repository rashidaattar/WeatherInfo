package com.demo.weatherinfo.data.model

import com.google.gson.annotations.SerializedName



data class ForecastResponse(

    @SerializedName("city")
    val city: City?,

    @SerializedName("cnt")
    val cnt: Int?,

    @SerializedName("cod")
    val cod: String?,

    @SerializedName("message")
    val message: Double?,

    @SerializedName("list")
    val list: List<ListItem>?
)

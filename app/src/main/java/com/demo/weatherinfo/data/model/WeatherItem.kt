package com.demo.weatherinfo.data.model

import com.google.gson.annotations.SerializedName

data class WeatherItem(

    @SerializedName("icon")
    val icon: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("main")
    val main: String?,

    @SerializedName("id")
    val id: Int?
) {

    fun getDescriptionText(): String? {
        return description?.capitalize()
    }
}

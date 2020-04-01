package com.demo.weatherinfo.data.model

import com.google.gson.annotations.SerializedName


data class Snow(

    @SerializedName("3h")
    val jsonMember3h: Double?
)

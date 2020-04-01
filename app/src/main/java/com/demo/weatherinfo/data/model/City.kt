package com.demo.weatherinfo.data.model

import com.google.gson.annotations.SerializedName


data class City(

    @SerializedName("country")
    val country: String?,

    @SerializedName("coord")
    val coord: Coord?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("id")
    val id: Int?
) 

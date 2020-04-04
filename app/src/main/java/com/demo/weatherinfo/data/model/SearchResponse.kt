package com.demo.weatherinfo.data.model

import com.google.gson.annotations.SerializedName


data class SearchResponse(

    @SerializedName("hits")
    val hits: List<HitsItem?>? = null
)

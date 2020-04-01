package com.demo.weatherinfo.data.model

import com.faskn.app.weatherapp.domain.model.Country
import com.google.gson.annotations.SerializedName


data class HighlightResult(

    @SerializedName( "country")
    val country: Country? = null,

    @SerializedName( "name")
    val name: Name? = null
)

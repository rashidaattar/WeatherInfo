package com.demo.weatherinfo.data.model

import com.google.gson.annotations.SerializedName


data class Name(

    @SerializedName( "matchLevel")
    val matchLevel: String? = null,

    @SerializedName( "fullyHighlighted")
    val fullyHighlighted: Boolean? = null,

    @SerializedName( "value")
    val value: String? = null,

    @SerializedName( "matchedWords")
    val matchedWords: List<String?>? = null
)

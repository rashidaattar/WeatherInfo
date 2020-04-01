package com.demo.weatherinfo.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

import kotlinx.android.parcel.Parcelize

data class Rain(

    @SerializedName("3h")
    val jsonMember3h: Double?
)

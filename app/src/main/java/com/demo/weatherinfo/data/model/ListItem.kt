package com.demo.weatherinfo.data.model

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*


data class ListItem(

    @SerializedName("dt")
    val dt: Long?,

    @SerializedName("rain")
    val rain: Rain?,

    @SerializedName("dt_txt")
    val dtTxt: String?,

    @SerializedName("snow")
    val snow: Snow?,

    @SerializedName("weather")
    val weather: List<WeatherItem?>?,

    @SerializedName("main")
    val main: Main?,

    @SerializedName("clouds")
    val clouds: Clouds?,

    @SerializedName("sys")
    val sys: Sys?,

    @SerializedName("wind")
    val wind: Wind?,

    @SerializedName("name")
    val name: String?
) {
    fun getWeatherItem(): WeatherItem? {
        return weather?.first()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDay(): String? {
        return dt?.let { getDateTime(it)?.getDisplayName(TextStyle.FULL, Locale.getDefault()) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDateTime(s: Long): DayOfWeek? {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s * 1000)
            val formattedDate = sdf.format(netDate)

            LocalDate.of(
                formattedDate.substringAfterLast("/").toInt(),
                formattedDate.substringAfter("/").take(2).toInt(),
                formattedDate.substringBefore("/").toInt()
            )
                .dayOfWeek
        } catch (e: Exception) {
            e.printStackTrace()
            DayOfWeek.MONDAY
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getColor(): Int {
        return when (dt?.let { getDateTime(it) }) {
            DayOfWeek.MONDAY -> Color.parseColor("#28E0AE")
            DayOfWeek.TUESDAY -> Color.parseColor("#FF0090")
            DayOfWeek.WEDNESDAY -> Color.parseColor("#FFAE00")
            DayOfWeek.THURSDAY -> Color.parseColor("#0090FF")
            DayOfWeek.FRIDAY -> Color.parseColor("#DC0000")
            DayOfWeek.SATURDAY -> Color.parseColor("#0051FF")
            DayOfWeek.SUNDAY -> Color.parseColor("#3D28E0")
            else -> Color.parseColor("#28E0AE")
        }
    }

    fun getHourColor(): Int {
        return when (dtTxt?.substringAfter(" ")?.substringBeforeLast(":")) {
            "00:00" -> Color.parseColor("#28E0AE")
            "03:00" -> Color.parseColor("#FF0090")
            "06:00" -> Color.parseColor("#FFAE00")
            "09:00" -> Color.parseColor("#0090FF")
            "12:00" -> Color.parseColor("#DC0000")
            "15:00" -> Color.parseColor("#0051FF")
            "18:00" -> Color.parseColor("#3D28E0")
            "21:00" -> Color.parseColor("#50E3FE")
            else -> Color.parseColor("#28E0AE")
        }
    }

    fun getHourOfDay(): String {
        return dtTxt?.substringAfter(" ")?.substringBeforeLast(":") ?: "00:00"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDayofWeek(): DayOfWeek {
        return LocalDate.parse(dtTxt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).dayOfWeek
    }
}

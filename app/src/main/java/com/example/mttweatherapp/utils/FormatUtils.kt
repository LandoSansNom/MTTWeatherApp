package com.example.mttweatherapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Yellow
import com.example.mttweatherapp.R
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import kotlin.math.roundToInt
import java.util.*

//@RequiresApi(Build.VERSION_CODES.O)
//fun getFormattedDate(date: Int): String {
//    val dateTime = getDateTimeFromEpoch(date)
//    return formatDateParts(getDateFromDateTime(dateTime))
//}
//
//fun getFormattedTime(date: Int): String {
//    val dateTime = getDateTimeFromEpoch(date)
//    return getTimeFromDateTime(dateTime)
//}

@RequiresApi(Build.VERSION_CODES.O)
fun getDayFromDate(date: Long): String {
    val newDate = getDateFromDateTime(getDateTimeFromEpoch(date))
    return getWeekDayName(newDate).substring(0, 3)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getWeekDayName(date: String): String {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("u-M-d", Locale.ENGLISH)
    return LocalDate.parse(date, formatter).dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
}

fun mapMonthNumberToName(monthNumber: String): String {
    return when (monthNumber) {
        "01" -> "January"
        "02" -> "February"
        "03" -> "March"
        "04" -> "April"
        "05" -> "May"
        "06" -> "June"
        "07" -> "July"
        "08" -> "August"
        "09" -> "September"
        "10" -> "October"
        "11" -> "November"
        "12" -> "December"
        else -> "NA"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateParts(date: String): String {
    val datePartsArray = date.split('-')
    val formattedDateNumber = datePartsArray[2]
    val formattedMonth = mapMonthNumberToName(datePartsArray[1])
    val formattedDay = getWeekDayName(date)
    return "${formattedDay}, $formattedDateNumber $formattedMonth"
}

fun getDateFromDateTime(dateTime: String): String {
    var i = 0
    var date = ""
    while (dateTime[i] != 'T') {
        date += dateTime[i]
        i++
    }
    return date
}

fun getTimeFromDateTime(dateTime: String): String {
    var i = 0
    while (dateTime[i] != 'T') i++
    return dateTime.substring(i + 1, dateTime.length)
}

fun getDateTimeFromEpoch(date: Long): String {
    return Instant
        .fromEpochSeconds(date.toLong())
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .toString()
}

fun getTemperatureInCelsius(temp: Double): String {
    var formattedTemp = temp - 273.15
    formattedTemp = (formattedTemp * 100.0).roundToInt() / 100.0
    formattedTemp = String.format("%.1f", formattedTemp).toDouble()
    return "${formattedTemp}\u00B0C"
}

fun getTemperatureInCelsiusInteger(temp: Double): String {
    var formattedTemp = temp - 273.15
    formattedTemp = (formattedTemp * 100.0).roundToInt() / 100.0
    val newFormattedTemp = formattedTemp.toInt()
    return "$newFormattedTemp"
}


fun getFormattedUVRange(uv: Double): String {
    return when {
        uv <= 2 -> "Low"
        uv > 2 && uv <= 5 -> "Moderate"
        uv > 5 && uv <= 7 -> "High"
        uv > 7 && uv <= 11 -> "Very High"
        else -> "Extreme"
    }
}

fun getUVIndexColor(level: String): Color {
    return when (level) {
        "Low" -> Color.Green
        "Moderate" -> Color.Yellow
        "High" -> Yellow
        "Very High" -> Color.Red
        "Extreme" -> Magenta
        else -> Color.White
    }
}

fun getHumidityInPercent(humidity: Int): String {
    return "${humidity}%"
}

fun getImageFromUrl(url: String): Int {
    return when (url) {
        "01d" -> R.drawable.oned1
        "01n" -> R.drawable.onen
        "02d" -> R.drawable.twod
        "02n" -> R.drawable.twon
        "03d", "03n" -> R.drawable.threedn
        "04d", "04n" -> R.drawable.fourdn
        "09d", "09n" -> R.drawable.ninedn
        "10d", "10n" -> R.drawable.tend
        "11d", "11n" -> R.drawable.elevend
        "13d", "13n" -> R.drawable.thirteend
        "50d", "50n" -> R.drawable.fiftydn
        else -> R.drawable.ic_launcher_foreground
    }
}
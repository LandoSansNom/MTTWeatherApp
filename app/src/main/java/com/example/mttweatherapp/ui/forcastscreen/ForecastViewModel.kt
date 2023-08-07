package com.example.mttweatherapp.ui.forcastscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mttweatherapp.data.repository.WeatherRepository
import com.example.mttweatherapp.model.DailyWeatherList

import com.example.mttweatherapp.model.WeatherItem

import com.example.mttweatherapp.utils.Resource
import com.example.mttweatherapp.utils.getDayFromDate

import com.example.mttweatherapp.utils.getTemperatureInCelsiusInteger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    var dailyWeatherList = mutableStateOf<List<DailyWeatherList>>(listOf())

    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")
    var tomorrowWeatherType = mutableStateOf("")
    var tomorrowTempHigh = mutableStateOf("")
    var tomorrowTempLow = mutableStateOf("")
    var tomorrowImgUrl = mutableStateOf("")

    init {
        loadDailyWeatherData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadDailyWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getWeatherInfo("London")) {
                is Resource.Success -> {
                    val weatherResponse = result.data?.execute()?.body()
                    weatherResponse?.let {
                        val dailyEntry = it.list.mapIndexed { _, entry ->
                            val day = getDayFromDate(entry.dt)
                            val imgUrl = entry.weather[0].icon
                            val weatherType = entry.weather[0].main
                            val highTemp = getTemperatureInCelsiusInteger(entry.main.temp_max)
                            val lowTemp = getTemperatureInCelsiusInteger(entry.main.temp_min)
                            DailyWeatherList(day, imgUrl, weatherType, highTemp, lowTemp)
                        }
                        dailyWeatherList.value = dailyEntry
                        if (dailyEntry.size >= 1) {
                            tomorrowWeatherType.value = dailyEntry[0].weatherType
                            tomorrowTempHigh.value = dailyEntry[0].maxTemp
                            tomorrowTempLow.value = dailyEntry[0].minTemp
                            tomorrowImgUrl.value = dailyEntry[0].img
                        }
                        if (dailyEntry.size > 1) {
                            dailyWeatherList.value =
                                dailyEntry.subList(1, minOf(dailyEntry.size, 8))
                        } else {
                            dailyWeatherList.value = emptyList()
                        }
                        loadError.value = ""
                        isLoading.value = false
                    } ?: run {
                        // Handle case where weatherResponse is null
                        loadError.value = "Weather data not available."
                        isLoading.value = false
                    }
                }

                is Resource.Error -> {
                    Timber.e("error")
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }
}
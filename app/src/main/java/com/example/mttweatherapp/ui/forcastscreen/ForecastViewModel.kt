package com.example.mttweatherapp.ui.forcastscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mttweatherapp.data.repository.WeatherRepository
import com.example.mttweatherapp.model.DailyWeatherList

import com.example.mttweatherapp.model.WeatherItem
import com.example.mttweatherapp.model.WeatherResponse

import com.example.mttweatherapp.utils.Resource
import com.example.mttweatherapp.utils.getDayFromDate

import com.example.mttweatherapp.utils.getTemperatureInCelsiusInteger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import timber.log.Timber
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    var dailyWeatherList = mutableStateOf<List<WeatherItem>>(listOf())

    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")
    var tomorrowWeatherType = mutableStateOf("")
    var tomorrowTempHigh = mutableStateOf("")
    var tomorrowTempLow = mutableStateOf("")
    var tomorrowImgUrl = mutableStateOf("")

    init {
        loadDailyWeatherData()
    }

    private fun handleWeatherData(response: WeatherResponse?) {
        if (response != null) {
            val dailyWeatherList = response.list
            if (dailyWeatherList.isNotEmpty()) {
                val tomorrowWeather = dailyWeatherList.getOrNull(0)
                tomorrowWeatherType.value = tomorrowWeather?.weather?.firstOrNull()?.main ?: ""
                tomorrowTempHigh.value = getTemperatureInCelsiusInteger(tomorrowWeather?.main?.temp_max ?: 0.0)
                tomorrowTempLow.value = getTemperatureInCelsiusInteger(tomorrowWeather?.main?.temp_min ?: 0.0)
                tomorrowImgUrl.value = tomorrowWeather?.weather?.firstOrNull()?.icon ?: ""
                this@ForecastViewModel.dailyWeatherList.value = dailyWeatherList.subList(1, minOf(dailyWeatherList.size, 8))
            }
        }
    }

    fun loadDailyWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                // Move the network call to the IO dispatcher using withContext
                withContext(Dispatchers.IO) {
                    val result = repository.getWeatherInfo("London")
                    handleWeatherData(result.data) // Handle the result on the main thread
                }
            } catch (e: Exception) {
                Timber.e(e, "Error fetching weather data")
                loadError.value = "Unknown Error occurred."
            } finally {
                isLoading.value = false
            }
        }
    }
}
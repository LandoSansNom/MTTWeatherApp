package com.example.mttweatherapp.ui.forcastscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mttweatherapp.data.repository.WeatherRepository
import com.example.mttweatherapp.model.Weather
import com.example.mttweatherapp.model.WeatherItem
import com.example.mttweatherapp.model.WeatherList
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

    fun loadDailyWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getWeatherInfo("London")) {
                is Resource.Success -> {
                    val forecast = result.data // This will give you the WeatherResponse object
                    if (forecast != null) {
                        val dailyWeatherList = forecast.list
                        if (dailyWeatherList.isNotEmpty()) {
                            val tomorrowWeather = dailyWeatherList.getOrNull(0)
                            tomorrowWeatherType.value =
                                tomorrowWeather?.weather?.firstOrNull()?.main ?: ""
                            tomorrowTempHigh.value = getTemperatureInCelsiusInteger(
                                tomorrowWeather?.main?.temp_max ?: 0.0
                            )
                            tomorrowTempLow.value = getTemperatureInCelsiusInteger(
                                tomorrowWeather?.main?.temp_min ?: 0.0
                            )
                            tomorrowImgUrl.value =
                                tomorrowWeather?.weather?.firstOrNull()?.icon ?: ""
                            this@ForecastViewModel.dailyWeatherList.value =
                                dailyWeatherList.subList(1, 5)
                        }
                    }
                    loadError.value = ""
                    isLoading.value = false
                }

                is Resource.Error -> {
                    Timber.e("error")
                    loadError.value = result.message ?: ""
                    isLoading.value = false
                }
            }
        }
    }
}

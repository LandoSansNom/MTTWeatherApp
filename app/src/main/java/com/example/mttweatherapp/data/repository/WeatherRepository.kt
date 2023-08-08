package com.example.mttweatherapp.data.repository
import com.example.mttweatherapp.data.remote.ApiCalls
import com.example.mttweatherapp.data.remote.ApiDetails.Companion.API_KEY
import com.example.mttweatherapp.model.City
import com.example.mttweatherapp.model.WeatherList
import com.example.mttweatherapp.model.WeatherResponse
import com.example.mttweatherapp.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Call
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class WeatherRepository @Inject constructor(
    private val api: ApiCalls
) {
    suspend fun getWeatherInfo(
        city: String
    ): Resource<WeatherResponse?> { // Update the return type here
        return try {
            val response = api.getWeatherByCity(city, API_KEY)
            Resource.Success(response.body()) // Use response.body() to get the WeatherResponse
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("Unknown Error occurred.")
        }
    }
}

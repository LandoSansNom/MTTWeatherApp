package com.example.mttweatherapp.data.repository
import com.example.mttweatherapp.data.remote.ApiCalls
import com.example.mttweatherapp.data.remote.ApiDetails.Companion.API_KEY
import com.example.mttweatherapp.model.WeatherResponse
import com.example.mttweatherapp.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Call
import javax.inject.Inject

@ActivityScoped
class WeatherRepository @Inject constructor(
    private val api: ApiCalls
) {
    suspend fun getWeatherInfo(
        city: String
    ): Resource<Call<WeatherResponse>> {
        return try {
            val response = api.getWeatherByCity(city, API_KEY)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Unknown Error occurred.")
        }
    }
}

package com.example.mttweatherapp.data.remote

import com.example.mttweatherapp.model.ForeCast
import com.example.mttweatherapp.model.WeatherList
import com.example.mttweatherapp.model.WeatherResponse
import com.example.mttweatherapp.utils.Resource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiCalls {
    @GET("forecast?")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") appid: String = ApiDetails.API_KEY
    ): Response<WeatherResponse> // Update the return type to Response<WeatherResponse>
}
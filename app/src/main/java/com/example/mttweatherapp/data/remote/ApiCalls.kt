package com.example.mttweatherapp.data.remote

import com.example.mttweatherapp.model.ForeCast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCalls {
    @GET("forecast?")
    fun getWeatherByCity(
        @Query("q")
        city: String,
        @Query("appid")
        appid: String = ApiDetails.API_KEY

    ): Call<ForeCast>



}
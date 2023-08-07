package com.example.mttweatherapp.model

data class DailyWeatherList(
    val day: String,
    val img: String,
    val weatherType: String,
    val maxTemp: String,
    val minTemp: String
)
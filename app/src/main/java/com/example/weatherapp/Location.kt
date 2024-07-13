package com.example.weatherapp

data class WeatherResponse(
    val data: List<WeatherData>
)

data class WeatherData(
    val temp: Double,
    val city_name: String
)

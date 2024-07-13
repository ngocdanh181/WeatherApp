package com.example.weatherapp

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("current")
    suspend fun getResponse
                (@Query("lat") latitude: Double,
                 @Query("lon") longitude: Double,
                 @Query("key") apiKey: String): WeatherResponse

}
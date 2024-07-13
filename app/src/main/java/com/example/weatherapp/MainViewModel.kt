package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val api = RetrofitInstance.api
    private val apiKey = "enter your api key from WeatherBitApi"

    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> get() = _weather

    fun getWeather(latitude: Double, longitude: Double) = viewModelScope.launch {
        try {
            val response = api.getResponse(latitude, longitude, apiKey)
            _weather.postValue(response)
        } catch (e: Exception) {
            // Handle the error appropriately
        }
    }
}
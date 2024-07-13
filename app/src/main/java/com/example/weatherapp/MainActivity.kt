package com.example.weatherapp

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.weatherapp.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.btVar1.setOnClickListener {
            obtainLocation()
        }
        mainViewModel.weather.observe(this,Observer{weatherResponse->
            val weatherData = weatherResponse?.data?.firstOrNull()
            weatherData?.let {
                binding.textView.text = "${it.temp} deg Celsius in ${it.city_name}"
            } ?: run {
                binding.textView.text = "Unable to get weather data"
            }

        })
    }

    @SuppressLint("MissingPermission")
    private fun obtainLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    mainViewModel.getWeather(location.latitude, location.longitude)
                }
            }
    }

}

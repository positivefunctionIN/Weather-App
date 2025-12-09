package com.example.weatherapp.network

import com.google.gson.annotations.SerializedName

// Main response from API
data class WeatherResponse(
    val name: String,                    // City name
    val main: Main,                      // Temperature data
    val weather: List<Weather>,          // Weather conditions
    val wind: Wind,                      // Wind data
)

// Temperature and pressure data
data class Main(
    val temp: Double,                    // Current temperature
    val humidity: Int,                   // Humidity percentage
)

// Weather condition (rain, sun, clouds, etc.)
data class Weather(
    val description: String,             // Example: "clear sky"
    val icon: String                     // Icon code like "01d"
)

// Wind information
data class Wind(
    val speed: Double                    // Wind speed in m/s
)
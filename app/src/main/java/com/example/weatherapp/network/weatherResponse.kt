package com.example.weatherapp.network

import com.google.gson.annotations.SerializedName

// Main response from API
data class WeatherResponse(
    val name: String,                    // City name
    val main: Main,                      // Temperature data
    val weather: List<Weather>,          // Weather conditions
    val wind: Wind,                      // Wind data
    val sys: Sys                         // Country info
)

// Temperature and pressure data
data class Main(
    val temp: Double,                    // Current temperature
    val humidity: Int,                   // Humidity percentage
    val pressure: Int,                   // Atmospheric pressure

    @SerializedName("feels_like")        // API uses "feels_like", we use feelsLike
    val feelsLike: Double
)

// Weather condition (rain, sun, clouds, etc.)
data class Weather(
    val main: String,                    // Example: "Clear", "Rain"
    val description: String,             // Example: "clear sky"
    val icon: String                     // Icon code like "01d"
)

// Wind information
data class Wind(
    val speed: Double                    // Wind speed in m/s
)

// System info (country, sunrise, sunset)
data class Sys(
    val country: String                  // Country code like "GB", "US"
)
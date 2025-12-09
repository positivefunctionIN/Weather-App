package com.example.weatherapp.network

import android.util.Log
import com.example.weatherapp.WeatherData

class WeatherRepository {
    private val apiService = RetrofitClient.weatherApi
    
    // 👇 YOUR API KEY IS HERE
    private val API_KEY = "8438e21908bb77877b3b6b5e9461dffb" 

    suspend fun getWeatherByCity(cityName: String): Result<WeatherData> {
        return try {
            Log.d("WeatherRepo", "🌍 Fetching weather for: $cityName")

            // Direct call - Retrofit handles success/failure automatically
            val response = apiService.getWeatherByCity(cityName, API_KEY)

            Log.d("WeatherRepo", "✅ Got response for: ${response.name}")

            val weatherData = WeatherData(
                city = response.name,
                temperature = response.main.temp,
                condition = response.weather.firstOrNull()?.description ?: "Unknown",
                humidity = response.main.humidity,
                windSpeed = response.wind.speed,
                icon = response.weather.firstOrNull()?.icon ?: "01d"
            )

            Log.d("WeatherRepo", "🌡️ Temperature: ${weatherData.temperature}°C")
            Result.success(weatherData)

        } catch (e: retrofit2.HttpException) {
            // HTTP errors (404, 500, etc.)
            Log.e("WeatherRepo", "❌ HTTP Error: ${e.code()} - ${e.message()}")
            when (e.code()) {
                404 -> Result.failure(Exception("City not found"))
                401 -> Result.failure(Exception("Invalid API key"))
                else -> Result.failure(Exception("Server error: ${e.code()}"))
            }
        } catch (e: java.io.IOException) {
            // Network errors (no internet, timeout, etc.)
            Log.e("WeatherRepo", "❌ Network Error: ${e.message}")
            Result.failure(Exception("No internet connection"))
        } catch (e: Exception) {
            // Other errors
            Log.e("WeatherRepo", "❌ Unexpected Error: ${e.message}", e)
            Result.failure(Exception("Something went wrong: ${e.message}"))
        }
    }
}
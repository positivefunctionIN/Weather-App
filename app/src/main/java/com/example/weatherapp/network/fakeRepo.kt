package com.example.weatherapp.network

import com.example.weatherapp.WeatherData
import kotlinx.coroutines.delay

class FakeWeatherRepository {

    // Fake weather data for different cities
    private val fakeWeatherData = mapOf(
        "london" to WeatherData(
            city = "London",
            temperature = 15.5,
            condition = "cloudy",
            humidity = 72,
            windSpeed = 5.8,
            icon = "04d"
        ),
        "paris" to WeatherData(
            city = "Paris",
            temperature = 18.2,
            condition = "clear sky",
            humidity = 65,
            windSpeed = 3.2,
            icon = "01d"
        ),
        "tokyo" to WeatherData(
            city = "Tokyo",
            temperature = 22.0,
            condition = "partly cloudy",
            humidity = 78,
            windSpeed = 4.5,
            icon = "02d"
        ),
        "new york" to WeatherData(
            city = "New York",
            temperature = 12.8,
            condition = "rainy",
            humidity = 85,
            windSpeed = 6.3,
            icon = "10d"
        ),
        "mumbai" to WeatherData(
            city = "Mumbai",
            temperature = 28.5,
            condition = "sunny",
            humidity = 70,
            windSpeed = 4.0,
            icon = "01d"
        ),
        "sydney" to WeatherData(
            city = "Sydney",
            temperature = 20.3,
            condition = "clear sky",
            humidity = 60,
            windSpeed = 5.2,
            icon = "01d"
        ),
        "delhi" to WeatherData(
            city = "Delhi",
            temperature = 25.0,
            condition = "haze",
            humidity = 68,
            windSpeed = 3.5,
            icon = "50d"
        ),
        "berlin" to WeatherData(
            city = "Berlin",
            temperature = 10.5,
            condition = "overcast clouds",
            humidity = 75,
            windSpeed = 4.8,
            icon = "04d"
        )
    )

    // Simulate network delay and fetch weather
    suspend fun getWeatherByCity(cityName: String): Result<WeatherData> {
        // Simulate network delay (like real API)
        delay(1500) // 1.5 seconds delay

        // Convert to lowercase for matching
        val city = cityName.trim().lowercase()

        // Check if city exists in our fake data
        return if (fakeWeatherData.containsKey(city)) {
            Result.success(fakeWeatherData[city]!!)
        } else {
            Result.failure(Exception("City not found. Try: London, Paris, Tokyo, New York, Mumbai, Sydney, Delhi, or Berlin"))
        }
    }
}
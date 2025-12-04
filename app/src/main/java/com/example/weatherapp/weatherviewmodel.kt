package com.example.weatherapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.FakeWeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() { // Renamed to follow conventions

    // Corrected the class name to match the import
    private val repository = FakeWeatherRepository()

    // Private mutable state
    private val _weatherData = mutableStateOf(
        WeatherData(
            city = "Search for a city",
            temperature = 0.0,
            condition = "",
            humidity = 0,
            windSpeed = 0.0,
            icon = "01d"
        )
    )

    // Public read-only state
    val weatherData: State<WeatherData> = _weatherData

    // Loading state
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    // Error state
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    // Function to fetch weather
    fun fetchWeather(cityName: String) {
        if (cityName.isBlank()) {
            _errorMessage.value = "Please enter a city name"
            return
        }

        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                val result = repository.getWeatherByCity(cityName)

                result.fold(
                    onSuccess = { data ->
                        _weatherData.value = data
                        _isLoading.value = false
                        Log.d("WeatherViewModel", "Success: $data")
                    },
                    onFailure = { exception ->
                        _errorMessage.value = exception.message
                        _isLoading.value = false
                        Log.e("WeatherViewModel", "Error: ${exception.message}")
                    }
                )
            } catch (e: Exception) {
                _errorMessage.value = "Unexpected error: ${e.message}"
                _isLoading.value = false
                Log.e("WeatherViewModel", "Exception: ${e.message}", e)
            }
        }
    }
}

data class WeatherData(
    val city: String,
    val temperature: Double,
    val condition: String,
    val humidity: Int,
    val windSpeed: Double,
    val icon: String
)
package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    val viewModel: WeatherViewModel = viewModel()

                    WeatherScreen(
                        modifier = Modifier.padding(innerPadding),
                        weatherData = viewModel.weatherData.value,
                        isLoading = viewModel.isLoading.value,
                        errorMessage = viewModel.errorMessage.value,
                        onSearch = { city ->
                            viewModel.fetchWeather(city)
                        }
                    )
                }
            }
        }
    }
}
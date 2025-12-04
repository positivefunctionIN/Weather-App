package com.example.weatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier, 
    weatherData: WeatherData,
    onSearch: (String) -> Unit // Added callback for search
) {
    val cityName = weatherData.city
    val temperature = weatherData.temperature
    val condition = weatherData.condition
    
    // State for the search text field
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    // The root Box should fill the entire screen to allow the background to be edge-to-edge.
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.weathersun),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.8f
        )

        // The `modifier` parameter (containing system bar padding) is applied to the Column.
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp), 
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Changed to Top to put search bar at top
        ) {
            // Search Bar
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Search City") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .background(Color.White.copy(alpha = 0.5f)), // Semi-transparent background for readability
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch(text)
                        keyboardController?.hide()
                    }
                ),
                trailingIcon = {
                    IconButton(onClick = { 
                        onSearch(text) 
                        keyboardController?.hide()
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )

            Spacer(modifier = Modifier.height(100.dp)) // Add some space between search and content

            // Weather Content
            Text(text = cityName, fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "${temperature.toInt()}°C", 
                fontSize = 48.sp
            )
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = condition, fontSize = 24.sp)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherScreen(
        weatherData = WeatherData(
            city = "London",
            temperature = 25.0, 
            condition = "Sunny",
            humidity = 80,      
            windSpeed = 5.0,    
            icon = "01d"
        ),
        onSearch = {}
    )
}
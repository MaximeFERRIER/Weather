package fr.droidfactory.weather.features.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeatherDetailsScreen(cityName: String, navigateUp: () -> Unit) {
     //Get the data
    val temperature = 15.8

    //Use the data
    WeatherDetails(city = cityName, temperature = temperature)
}

@Composable
private fun WeatherDetails(
    city: String,
    temperature: Double
) {

    var isCelsius by rememberSaveable { mutableStateOf(true) }

    val displayedTemperature = if(isCelsius) {
        "$temperature °C"
    } else {
        "${temperature.toFahrenheit()} °F"
    }

    Column (
        modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.align(Alignment.End), verticalAlignment = Alignment.CenterVertically) {

            Text(text = "°F", style = MaterialTheme.typography.subtitle1)

            Switch(checked = isCelsius, onCheckedChange = { isCelsius = !isCelsius })

            Text(text = "°C", style = MaterialTheme.typography.subtitle1)
        }

        Text(
            text = city,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )
        Text(
            text = displayedTemperature,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.error
        )
    }
}

private fun Double.toFahrenheit() = this * 1.8 + 32.0
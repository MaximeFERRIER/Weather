package fr.droidfactory.weather.features.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Water
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import fr.droidfactory.weather.R
import fr.droidfactory.weather.features.details.WeatherDetailsState

@Composable
internal fun ItemWeatherDetails(state: WeatherDetailsState) {
    requireNotNull(state.cityDetailsResultEntity)
    val cityDetails = state.cityDetailsResultEntity
    val isCelsius = state.isCelsiusState

    requireNotNull(cityDetails.cityInfoEntity)

    val displayedTemperature = if(isCelsius) {
        "${cityDetails.cityInfoEntity.temperature} °C"
    } else {
        "${cityDetails.cityInfoEntity.temperatureF} °F"
    }

    val displayedFeelTemperature = if(isCelsius) {
        "${cityDetails.cityInfoEntity.temperatureFeelLike} °C"
    } else {
        "${cityDetails.cityInfoEntity.temperatureFeelLikeF} °F"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally),
            painter = rememberImagePainter(
                data = cityDetails.cityInfoEntity.conditionIcon,
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.ic_loader)
                    error(R.drawable.ic_error)
                }
            ),
            contentDescription = cityDetails.cityInfoEntity.conditionText
        )

        Text(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            text = displayedTemperature,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Start
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = cityDetails.cityInfoEntity.updateDate,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Start
        )

        Text(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            text = cityDetails.cityInfoEntity.conditionText,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Start
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)) {
            ItemCard(modifier = Modifier.weight(1f), icon = Icons.Default.Thermostat, title = stringResource(
                id = R.string.temperature_felt
            ), data = displayedFeelTemperature)

            ItemCard(modifier = Modifier.weight(1f), icon = Icons.Default.Water, title = stringResource(
                id = R.string.humidity
            ), data = "${cityDetails.cityInfoEntity.humidity}")

            ItemCard(modifier = Modifier.weight(1f), icon = Icons.Default.WbSunny, title = stringResource(
                id = R.string.uv
            ), data = "${cityDetails.cityInfoEntity.uv}")
        }
    }
}
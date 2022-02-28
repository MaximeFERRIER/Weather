package fr.droidfactory.weather.features.cities.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.droidfactory.weather.R
import fr.droidfactory.weather.features.cities.CitiesAction
import fr.droidfactory.weather.features.cities.CitiesScreenState
import fr.droidfactory.weather.features.cities.CitiesViewModel
import fr.droidfactory.weather.features.cities.OnCityClicked

private typealias CitiesActioner = (CitiesAction) -> Unit

@Composable
fun CitiesScreen(
    viewModel: CitiesViewModel = viewModel(),
    navigateToDetails: (String) -> Unit
) {

    val state by viewModel.citiesState.collectAsState()

    Cities(state) { action ->
        when (action) {
            is OnCityClicked -> navigateToDetails(action.cityName)
        }
    }
}

@Composable
private fun Cities(state: CitiesScreenState, actioner: CitiesActioner) {
    val cities = state.cities
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopCitiesAppBar()
        }) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            if (cities.isEmpty()) return@LazyColumn

            items(items = cities) { city ->

                ItemCity(city = city) {
                    actioner(OnCityClicked(cityName = city))
                }

                Divider()
            }
        }
    }
}

@Composable
private fun TopCitiesAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    )
}
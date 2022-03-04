package fr.droidfactory.weather.features.cities.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import fr.droidfactory.weather.R
import fr.droidfactory.weather.data.database.entities.CityEntity
import fr.droidfactory.weather.features.cities.*

private typealias CitiesActioner = (CitiesAction) -> Unit

@Composable
fun CitiesScreen(
    viewModel: CitiesViewModel,
    navigateToDetails: (String) -> Unit
) {

    val state by viewModel.citiesState.collectAsState()
    val dialogState = rememberSaveable { mutableStateOf(false) }

    Cities(state, dialogState) { action ->
        when (action) {
            is OnCityClicked -> navigateToDetails(action.cityName)
            is OnCityAdded -> {
                viewModel.insertCity(action.city)
                dialogState.value = false
            }
            OnCloseDialog -> dialogState.value = false
            OnOpenDialog -> dialogState.value = true
        }
    }
}

@Composable
private fun Cities(state: CitiesScreenState, dialogState: MutableState<Boolean>, actioner: CitiesActioner) {
    val cities = state.cities
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopCitiesAppBar()
        },
        floatingActionButton = {
            Fab(actioner = actioner)
        }) {

        if(dialogState.value) {
            NewCityDialog(
                onCityAdded = { city -> actioner(OnCityAdded(CityEntity(name = city, colorId = 0L))) },
                onDialogClose = { actioner(OnCloseDialog) }
            )
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            if (cities.isEmpty()) return@LazyColumn

            items(items = cities) { city ->

                ItemCity(city = city) {
                    actioner(OnCityClicked(cityName = city.cityEntity.name))
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

@Composable
private fun Fab(actioner: CitiesActioner) {
    FloatingActionButton(onClick = { actioner(OnOpenDialog) }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.fab_add_city)
        )
    }
}
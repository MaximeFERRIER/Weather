package fr.droidfactory.weather.features.details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fr.droidfactory.weather.R
import fr.droidfactory.weather.data.network.utils.ApiResult
import fr.droidfactory.weather.features.details.*
import kotlinx.coroutines.launch

private typealias WeatherDetailsActioner = (WeatherDetailsAction) -> Unit

@Composable
fun WeatherDetailsScreen(viewModel: WeatherDetailsViewModel, navigateUp: () -> Unit) {
    //Get the data
    val cityDetailsState by viewModel.viewState.collectAsState()

    ProvideWeatherErrorFormatter {
        WeatherDetails(cityDetailsState) { action ->
            when (action) {
                NavigateUp -> navigateUp()
                ToggleCelsius -> viewModel.toggleIsCelsius()
            }
        }
    }

}

@Composable
private fun WeatherDetails(state: WeatherDetailsState, actioner: WeatherDetailsActioner) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val city = state.cityDetailsResultEntity?.cityEntity
    val cityInfo = state.cityDetailsResultEntity?.cityInfoEntity
    val isCelsius = state.isCelsiusState

    val errorFormatter = LocalWeatherErrorFormatter.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopWeatherDetailsAppBar(
                city = city?.name ?: "",
                isCelsius = isCelsius,
                isEnabled = cityInfo != null,
                actioner = actioner
            )
        }
    ) {
        when (state.callState) {
            ApiResult.Loading, ApiResult.Uninitialized -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is ApiResult.Error -> {
                if (cityInfo == null) {
                    ItemError(errorMessage = errorFormatter.weatherError(state.callState.exception))
                } else {
                    ItemWeatherDetails(state = state)
                    LaunchedEffect(key1 = "error_snackbar") {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                errorFormatter.weatherError(state.callState.exception)
                            )
                        }
                    }
                }
            }
            is ApiResult.Success -> ItemWeatherDetails(state = state)
        }
    }
}

@Composable
private fun TopWeatherDetailsAppBar(
    city: String,
    isCelsius: Boolean,
    isEnabled: Boolean,
    actioner: WeatherDetailsActioner
) {

    TopAppBar(
        title = {
            Text(
                text = city,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { actioner(NavigateUp) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.navigation_back)
                )
            }
        },
        actions = {
            Text(text = "°F", style = MaterialTheme.typography.subtitle1)

            Switch(
                checked = isCelsius,
                onCheckedChange = { actioner(ToggleCelsius) },
                enabled = isEnabled
            )

            Text(text = "°C", style = MaterialTheme.typography.subtitle1)
        }
    )

}
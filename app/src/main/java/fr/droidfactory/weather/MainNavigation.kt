package fr.droidfactory.weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.droidfactory.weather.data.repository.CityRepository
import fr.droidfactory.weather.data.repository.ColorRepository
import fr.droidfactory.weather.features.cities.CitiesViewModel
import fr.droidfactory.weather.features.cities.CitiesViewModelFactory
import fr.droidfactory.weather.features.cities.domain.AddCityInteractorImpl
import fr.droidfactory.weather.features.cities.domain.ObserveCitiesInteractorImpl
import fr.droidfactory.weather.features.cities.ui.CitiesScreen
import fr.droidfactory.weather.features.details.WeatherDetailsViewModel
import fr.droidfactory.weather.features.details.WeatherDetailsViewModelFactory
import fr.droidfactory.weather.features.details.domains.GetCityWeatherInteractorImpl
import fr.droidfactory.weather.features.details.domains.ObserveCityByNameInteractorImpl
import fr.droidfactory.weather.features.details.ui.WeatherDetailsScreen

@Composable
fun MainNavigation(cityRepository: CityRepository, colorRepository: ColorRepository) {
    val navController by rememberUpdatedState(newValue = rememberNavController())

    NavHost(navController = navController, startDestination = NavScreen.Cities.route) {
        composable(NavScreen.Cities.route) {

            val vm = viewModel<CitiesViewModel>(
                factory = CitiesViewModelFactory(
                    observeCitiesInteractor = ObserveCitiesInteractorImpl(cityRepository = cityRepository),
                    addCityInteractor = AddCityInteractorImpl(cityRepository = cityRepository, colorRepository = colorRepository)
                )
            )

            CitiesScreen(viewModel = vm) { city ->
                navController.navigate("${NavScreen.Details.route}/$city")
            }
        }

        composable(
            route = NavScreen.Details.routeWithArgument,
            arguments = listOf(navArgument(NavScreen.Details.cityName) {
                type = NavType.StringType
            })
        ) { navBackstackEntry ->
            val cityName = navBackstackEntry.arguments?.getString(NavScreen.Details.cityName) ?: return@composable

            val vm = viewModel<WeatherDetailsViewModel>(
                factory = WeatherDetailsViewModelFactory(
                    city = cityName,
                    getCityWeatherInteractor = GetCityWeatherInteractorImpl(cityRepository),
                    observeCityByNameInteractor = ObserveCityByNameInteractorImpl(cityRepository)
                )
            )
            WeatherDetailsScreen(vm) {
                navController.navigateUp()
            }
        }
    }
}

sealed class NavScreen(val route: String) {
    object Cities: NavScreen("Cities")
    object Details: NavScreen("Details") {
        const val routeWithArgument: String = "Details/{cityName}"
        const val cityName = "cityName"
    }
}
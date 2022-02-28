package fr.droidfactory.weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.droidfactory.weather.features.cities.ui.CitiesScreen
import fr.droidfactory.weather.features.details.WeatherDetailsScreen

@Composable
fun MainNavigation() {
    val navController by rememberUpdatedState(newValue = rememberNavController())

    NavHost(navController = navController, startDestination = NavScreen.Cities.route) {
        composable(NavScreen.Cities.route) {
            CitiesScreen() { city ->
                navController.navigate("${NavScreen.Details.route}/$city ")
            }
        }

        composable(
            route = NavScreen.Details.routeWithArgument,
            arguments = listOf(navArgument(NavScreen.Details.cityName) {
                type = NavType.StringType
            })
        ) { navBackstackEntry ->
            val cityName = navBackstackEntry.arguments?.getString(NavScreen.Details.cityName) ?: return@composable
            WeatherDetailsScreen(cityName) {
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
package fr.droidfactory.weather.features.cities

sealed interface CitiesAction
data class OnCityClicked(val cityName: String): CitiesAction
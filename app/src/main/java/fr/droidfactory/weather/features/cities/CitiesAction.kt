package fr.droidfactory.weather.features.cities

import fr.droidfactory.weather.data.database.entities.CityEntity

sealed interface CitiesAction
data class OnCityClicked(val cityName: String): CitiesAction
object OnOpenDialog: CitiesAction
object OnCloseDialog: CitiesAction
data class OnCityAdded(val city: CityEntity): CitiesAction
package fr.droidfactory.weather.features.cities

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CitiesViewModel: ViewModel() {

    private val _citiesState = MutableStateFlow(CitiesScreenState())
    val citiesState: StateFlow<CitiesScreenState> = _citiesState

    init {
        initCities()
    }

    private fun initCities() {
        val cities = ArrayList<String>()
        cities.add("Lyon")
        cities.add("Toronto")
        cities.add("Tokyo")
        cities.add("Reykjavik")
        cities.add("Dublin")
        cities.add("Krung Thep Mahanakhon Amon Rattanakosin Mahinthara Ayuthaya Mahadilok Phop Noppharat Ratchathani Burirom Udomratchaniwet Mahasathan Amon Piman Awatan Sathit Sakkathattiya Witsanukam Prasit.")

        _citiesState.value = CitiesScreenState(cities)
    }
}

data class CitiesScreenState(val cities: List<String> = emptyList())
package fr.droidfactory.weather.features.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.droidfactory.weather.data.database.entities.CityDetailsResultEntity
import fr.droidfactory.weather.data.database.entities.CityEntity
import fr.droidfactory.weather.features.cities.domain.AddCityInteractor
import fr.droidfactory.weather.features.cities.domain.ObserveCitiesInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CitiesViewModel(observeCitiesInteractor: ObserveCitiesInteractor, private val addCityInteractor: AddCityInteractor): ViewModel() {

    private val _citiesState = MutableStateFlow(CitiesScreenState())
    internal val citiesState: StateFlow<CitiesScreenState> = _citiesState

    init {
        viewModelScope.launch {
            observeCitiesInteractor.observeCities().collect {
                _citiesState.value = CitiesScreenState(cities = it)
            }
        }
    }

    fun insertCity(cityEntity: CityEntity) {
        viewModelScope.launch {
            addCityInteractor.addCity(cityEntity = cityEntity)
        }
    }
}

internal data class CitiesScreenState(val cities: List<CityDetailsResultEntity> = emptyList())

@Suppress("UNCHECKED_CAST")
class CitiesViewModelFactory (
    private val observeCitiesInteractor: ObserveCitiesInteractor,
    private val addCityInteractor: AddCityInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CitiesViewModel::class.java)) {
            return CitiesViewModel(observeCitiesInteractor = observeCitiesInteractor, addCityInteractor = addCityInteractor) as T
        }
        throw IllegalArgumentException("Illegal ViewModel class")
    }

}
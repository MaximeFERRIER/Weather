package fr.droidfactory.weather.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.droidfactory.weather.data.database.entities.CityDetailsResultEntity
import fr.droidfactory.weather.data.network.utils.ApiResult
import fr.droidfactory.weather.features.details.domains.GetCityWeatherInteractor
import fr.droidfactory.weather.features.details.domains.ObserveCityByNameInteractor
import kotlinx.coroutines.flow.*

class WeatherDetailsViewModel(
    city: String,
    getCityWeatherInteractor: GetCityWeatherInteractor,
    observeCityByNameInteractor: ObserveCityByNameInteractor
) : ViewModel() {

    private val _isCelsius = MutableStateFlow(true)

    internal val viewState: StateFlow<WeatherDetailsState> = combine(
        getCityWeatherInteractor.getCityWeather(city),
        observeCityByNameInteractor.observeCityByName(city),
        _isCelsius
    ) { callState, cityDetailsResult, isCelsius ->
        WeatherDetailsState(
            callState = callState,
            cityDetailsResultEntity = cityDetailsResult,
            isCelsiusState = isCelsius
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, WeatherDetailsState())

    fun toggleIsCelsius() {
        _isCelsius.value = !_isCelsius.value
    }

}

internal data class WeatherDetailsState(
    val cityDetailsResultEntity: CityDetailsResultEntity? = null,
    val callState: ApiResult<Unit> = ApiResult.Uninitialized,
    var isCelsiusState: Boolean = true
)

@Suppress("UNCHECKED_CAST")
class WeatherDetailsViewModelFactory(
    private val city: String,
    private val getCityWeatherInteractor: GetCityWeatherInteractor,
    private val observeCityByNameInteractor: ObserveCityByNameInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherDetailsViewModel(
            city = city,
            getCityWeatherInteractor = getCityWeatherInteractor,
            observeCityByNameInteractor = observeCityByNameInteractor
        ) as T
    }

}
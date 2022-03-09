package fr.droidfactory.weather.features.details.domains

import fr.droidfactory.weather.data.network.utils.ApiResult
import fr.droidfactory.weather.data.repository.CityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetCityWeatherInteractor {
    fun getCityWeather(cityName: String): Flow<ApiResult<Unit>>
}

class GetCityWeatherInteractorImpl(
    private val cityRepository: CityRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GetCityWeatherInteractor {
    override fun getCityWeather(cityName: String): Flow<ApiResult<Unit>> =
        cityRepository.getCityWeather(cityName).flowOn(ioDispatcher)
}
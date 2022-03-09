package fr.droidfactory.weather.features.details.domains

import fr.droidfactory.weather.data.database.entities.CityDetailsResultEntity
import fr.droidfactory.weather.data.repository.CityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface ObserveCityByNameInteractor {
    fun observeCityByName(cityName: String): Flow<CityDetailsResultEntity>
}

class ObserveCityByNameInteractorImpl(
    private val cityRepository: CityRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ObserveCityByNameInteractor {
    override fun observeCityByName(cityName: String): Flow<CityDetailsResultEntity> =
        cityRepository.observeCityByName(cityName).flowOn(dispatcher)
}
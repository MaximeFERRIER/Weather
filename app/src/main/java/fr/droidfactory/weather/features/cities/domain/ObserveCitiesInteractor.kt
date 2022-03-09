package fr.droidfactory.weather.features.cities.domain

import fr.droidfactory.weather.data.database.entities.CityDetailsResultEntity
import fr.droidfactory.weather.data.database.entities.CityEntity
import fr.droidfactory.weather.data.repository.CityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface ObserveCitiesInteractor {
    fun observeCities(): Flow<List<CityDetailsResultEntity>>
}

class ObserveCitiesInteractorImpl(private val cityRepository: CityRepository, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    ObserveCitiesInteractor {
    override fun observeCities(): Flow<List<CityDetailsResultEntity>> = cityRepository.observeCities().flowOn(dispatcher)
}
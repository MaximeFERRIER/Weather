package fr.droidfactory.weather.features.cities.domain

import fr.droidfactory.weather.data.database.entities.CityDetailsResultEntity
import fr.droidfactory.weather.data.database.entities.CityEntity
import fr.droidfactory.weather.data.repository.CityRepository
import kotlinx.coroutines.flow.Flow

interface ObserveCitiesInteractor {
    fun observeCities(): Flow<List<CityDetailsResultEntity>>
}

class ObserveCitiesInteractorImpl(private val cityRepository: CityRepository) :
    ObserveCitiesInteractor {
    override fun observeCities(): Flow<List<CityDetailsResultEntity>> = cityRepository.observeCities()
}
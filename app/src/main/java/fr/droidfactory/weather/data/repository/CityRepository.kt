package fr.droidfactory.weather.data.repository

import fr.droidfactory.weather.data.database.dao.CityDao
import fr.droidfactory.weather.data.database.entities.CityEntity

class CityRepository(
    private val cityDao: CityDao
) {

    suspend fun insertCity(cityEntity: CityEntity) = cityDao.insertCity(cityEntity)

    fun observeCities() = cityDao.observeCities()
}
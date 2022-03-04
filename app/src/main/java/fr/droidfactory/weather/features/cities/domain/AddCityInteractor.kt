package fr.droidfactory.weather.features.cities.domain

import fr.droidfactory.weather.data.database.entities.CityEntity
import fr.droidfactory.weather.data.database.entities.ColorEntity
import fr.droidfactory.weather.data.repository.CityRepository
import fr.droidfactory.weather.data.repository.ColorRepository

interface AddCityInteractor {
    suspend fun addCity(cityEntity: CityEntity)
}

class AddCityInteractorImpl(
    private val cityRepository: CityRepository,
    private val colorRepository: ColorRepository
): AddCityInteractor {
    override suspend fun addCity(cityEntity: CityEntity) {
        val colorId = colorRepository.insertColor(
            ColorEntity(
                red = (0..255).random(),
                green = (0..255).random(),
                blue = (0..255).random(),
            )
        )
        cityRepository.insertCity(cityEntity = cityEntity.copy(colorId = colorId))
    }
}
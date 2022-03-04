package fr.droidfactory.weather.data.repository

import fr.droidfactory.weather.data.database.dao.ColorDao
import fr.droidfactory.weather.data.database.entities.ColorEntity

class ColorRepository(private val colorDao: ColorDao) {
    suspend fun insertColor(colorEntity: ColorEntity) = colorDao.insertColor(colorEntity = colorEntity)
}
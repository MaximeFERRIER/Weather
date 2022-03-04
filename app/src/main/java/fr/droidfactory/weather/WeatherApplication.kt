package fr.droidfactory.weather

import android.app.Application
import fr.droidfactory.weather.data.database.WeatherDatabase
import fr.droidfactory.weather.data.repository.CityRepository
import fr.droidfactory.weather.data.repository.ColorRepository

class WeatherApplication: Application() {
    val database by lazy { WeatherDatabase.getDatabase(context = this) }
    val cityRepository by lazy {
        CityRepository(
            cityDao = database.cityDao()
        )
    }
    val colorRepository by lazy {
        ColorRepository(colorDao = database.colorDao())
    }
}
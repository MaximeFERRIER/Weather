package fr.droidfactory.weather

import android.app.Application
import fr.droidfactory.weather.data.database.WeatherDatabase
import fr.droidfactory.weather.data.mappers.CityInfoMapper
import fr.droidfactory.weather.data.network.WeatherAPI
import fr.droidfactory.weather.data.network.endpoints.CityServices
import fr.droidfactory.weather.data.repository.CityRepository
import fr.droidfactory.weather.data.repository.ColorRepository

class WeatherApplication: Application() {
    val database by lazy { WeatherDatabase.getDatabase(context = this) }
    private val retrofit by lazy { WeatherAPI.getRetrofitclient() }
    val cityRepository by lazy {
        CityRepository(
            cityDao = database.cityDao(),
            cityServices = retrofit.create(CityServices::class.java),
            cityInfoDao = database.cityInfoDao(),
            cityInfoMapper = CityInfoMapper()
        )
    }
    val colorRepository by lazy {
        ColorRepository(colorDao = database.colorDao())
    }
}
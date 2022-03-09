package fr.droidfactory.weather.data.mappers

import fr.droidfactory.weather.data.database.entities.CityInfoEntity
import fr.droidfactory.weather.data.network.models.CityInfoResponse

class CityInfoMapper {
    fun map(cityName: String, cityInfoResponse: CityInfoResponse): CityInfoEntity = with(cityInfoResponse) {
        CityInfoEntity(
            cityName = cityName,
            updateDate = current.lastUpdated,
            temperature = current.tempC,
            conditionText = current.condition.text,
            conditionIcon = "https:${current.condition.icon}",
            temperatureFeelLike = current.feelslikeC,
            humidity = current.humidity,
            uv = current.uv
        )
    }
}
package fr.droidfactory.weather.data.repository

import fr.droidfactory.weather.data.database.dao.CityDao
import fr.droidfactory.weather.data.database.dao.CityInfoDao
import fr.droidfactory.weather.data.database.entities.CityEntity
import fr.droidfactory.weather.data.mappers.CityInfoMapper
import fr.droidfactory.weather.data.network.endpoints.CityServices
import fr.droidfactory.weather.data.network.utils.ApiResult
import fr.droidfactory.weather.data.network.utils.executeCall
import fr.droidfactory.weather.data.network.utils.toApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CityRepository(
    private val cityDao: CityDao,
    private val cityServices: CityServices,
    private val cityInfoDao: CityInfoDao,
    private val cityInfoMapper: CityInfoMapper
) {

    suspend fun insertCity(cityEntity: CityEntity) = cityDao.insertCity(cityEntity)

    fun observeCities() = cityDao.observeCities().flowOn(Dispatchers.IO)

    fun getCityWeather(cityName: String): Flow<ApiResult<Unit>> = flow {
        emit(ApiResult.Loading)
        val cityDetailsResponse = executeCall(
            call = { cityServices.getCityWeather(city = cityName) },
            errorHandler = { code, error -> code.toApiException(errorMessage = error) }
        )

        if (cityDetailsResponse is ApiResult.Error) {
            emit(ApiResult.Error(cityDetailsResponse.exception))
            return@flow
        }

        val cityInfoEntity = cityInfoMapper.map(
            cityName = cityName,
            cityInfoResponse = (cityDetailsResponse as ApiResult.Success).data
        )
        cityInfoDao.insertCityInfo(cityInfoEntity)
        emit(ApiResult.Success(Unit))
    }

    fun observeCityByName(cityName: String) = cityDao.observeCityByName(cityName)
}
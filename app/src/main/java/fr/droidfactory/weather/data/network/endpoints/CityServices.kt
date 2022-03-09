package fr.droidfactory.weather.data.network.endpoints

import fr.droidfactory.weather.data.network.models.CityInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityServices {
    @GET("current.json")
    suspend fun getCityWeather(@Query("q") city: String): Response<CityInfoResponse>
}
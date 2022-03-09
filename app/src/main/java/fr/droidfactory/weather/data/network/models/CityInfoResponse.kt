package fr.droidfactory.weather.data.network.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityInfoResponse(
    @Json(name = "location")
    val location: Location,
    @Json(name = "current")
    val current: Current
)
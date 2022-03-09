package fr.droidfactory.weather.data.network.utils

sealed class ApiException(override val message: String?) : Exception(message)

data class UnknownCityException(override val message: String?) : ApiException(message) //400
data class ApiKeyNotValidException(override val message: String?) : ApiException(message) //401
data class ForbiddenException(override val message: String?) : ApiException(message) //403
data class ServerException(override val message: String?) : ApiException(message) //500 - 599

data class TimeoutException(override val message: String?) : ApiException(message)
data class NoInternetConnectionException(override val message: String?) : ApiException(message)
data class UnknownException(override val message: String?) : ApiException(message)


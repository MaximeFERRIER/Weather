package fr.droidfactory.weather.data.network.utils

sealed interface ApiResult<out T> {
    object Uninitialized : ApiResult<Nothing>
    object Loading : ApiResult<Nothing>
    data class Success<out T>(val data: T) : ApiResult<T>
    data class Error(val exception: ApiException) : ApiResult<Nothing>
}
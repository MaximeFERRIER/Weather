package fr.droidfactory.weather.data.network.utils

import retrofit2.Response
import java.lang.Exception
import java.net.UnknownHostException

suspend fun <T> executeCall(
    call: suspend () -> Response<T>,
    errorHandler: (Int, String?) -> ApiException
): ApiResult<T> {
    return try {
        call().toApiResult(errorHandler)
    } catch (e: UnknownHostException) {
        ApiResult.Error(NoInternetConnectionException(null))
    } catch (e: Exception) {
        throw e
    }
}

fun <T> Response<T>.toApiResult(errorHandler: (Int, String?) -> ApiException) : ApiResult<T> {
    return if(isSuccessful) {
        ApiResult.Success(body()!!)
    } else {
        ApiResult.Error(errorHandler(code(), errorBody()?.string()))
    }
}

fun Int.toApiException(errorMessage: String? = null): ApiException {
    return when(this) {
        400 -> UnknownCityException(errorMessage)
        401 -> ApiKeyNotValidException(errorMessage)
        403 -> ForbiddenException(errorMessage)
        in 500..599 -> ServerException(errorMessage)
        else -> UnknownException(errorMessage)
    }
}
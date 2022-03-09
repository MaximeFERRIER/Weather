package fr.droidfactory.weather.features.details

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import fr.droidfactory.weather.R
import fr.droidfactory.weather.data.network.utils.*

internal val LocalWeatherErrorFormatter: ProvidableCompositionLocal<WeatherErrorFormatter> = staticCompositionLocalOf {
    error("WeatherErrorFormatter not provided")
}

internal class WeatherErrorFormatter (private val context: Context) {
    fun weatherError(error: ApiException): String = when(error) {
        is ApiKeyNotValidException -> context.getString(R.string.error_unauthorized)
        is ForbiddenException -> context.getString(R.string.error_forbidden)
        is NoInternetConnectionException -> context.getString(R.string.error_no_internet)
        is ServerException -> context.getString(R.string.error_server)
        is TimeoutException -> context.getString(R.string.error_timeout)
        is UnknownCityException -> context.getString(R.string.error_no_city_found)
        is UnknownException -> context.getString(R.string.error_unknown)
    }
}

@Composable
internal fun ProvideWeatherErrorFormatter(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val formatter = remember {
        WeatherErrorFormatter(context = context)
    }
    CompositionLocalProvider(LocalWeatherErrorFormatter provides formatter) {
        content()
    }
}
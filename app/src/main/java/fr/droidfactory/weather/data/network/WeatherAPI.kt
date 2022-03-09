package fr.droidfactory.weather.data.network

import com.squareup.moshi.Moshi
import fr.droidfactory.weather.BuildConfig
import fr.droidfactory.weather.data.network.interceptors.ApiKeyInterceptor
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class WeatherAPI {
    companion object {
        private const val BASE_URL = "https://api.weatherapi.com/v1/"
        private var INSTANCE: Retrofit? = null
        private var moshi = Moshi.Builder().build()
        private val okHttpInstance = WeatherOkHTTP.getOkHttpClient()

        fun getRetrofitclient(): Retrofit {
            if (INSTANCE == null) {
                val callFactory = Call.Factory { request ->
                    okHttpInstance.newCall(request = request)
                }
                INSTANCE = Retrofit.Builder().baseUrl(BASE_URL)
                    .callFactory(callFactory)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
            }

            return INSTANCE!!
        }
    }
}


private const val TIMEOUT = 4000L

class WeatherOkHTTP {
    companion object {
        private var INSTANCE: OkHttpClient? = null

        fun getOkHttpClient(): OkHttpClient {
            if (INSTANCE == null) {
                INSTANCE = OkHttpClient.Builder()
                    .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                    .addInterceptor(ApiKeyInterceptor())
                    .apply {
                        if (BuildConfig.DEBUG) {
                            val loggingInterceptor = HttpLoggingInterceptor().apply {
                                setLevel(HttpLoggingInterceptor.Level.BODY)
                            }
                            addInterceptor(loggingInterceptor)
                        }
                    }.build()
            }

            return INSTANCE!!
        }
    }
}
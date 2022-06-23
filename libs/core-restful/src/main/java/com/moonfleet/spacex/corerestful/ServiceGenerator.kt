package com.moonfleet.spacex.corerestful

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.BehaviorDelegate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
abstract class ServiceGenerator<T>() {

    private val retrofit: Retrofit
    private var service: T? = null
    private var mockService: BehaviorDelegate<T>? = null

    protected val retrofitBuilder: Retrofit.Builder
        get() = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .baseUrl(getBaseURL())
            .client(httpClient)

    protected val gsonBuilder: GsonBuilder
        get() = GsonBuilder().setLenient()

    protected val httpClient: OkHttpClient
        get() = httpClientBuilder.build()

    protected val httpClientBuilder: OkHttpClient.Builder
        get() {

            val httpClientBuilder = OkHttpClient.Builder()

            httpClientBuilder
                .readTimeout(TIME_OUT_MILLIS, TimeUnit.MILLISECONDS)
                .connectTimeout(TIME_OUT_MILLIS, TimeUnit.MILLISECONDS)

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)

            return httpClientBuilder
        }

    init {
        retrofit = retrofitBuilder.build()
    }

    protected fun getService(cls: Class<T>): T {
        if (service == null) {
            service = retrofit.create(cls)
        }
        return service!!
    }

    abstract fun getBaseURL(): String

    companion object {
        val TIME_OUT_MILLIS = 60000L
    }

}
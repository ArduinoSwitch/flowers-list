package com.setesh.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.setesh.data.network.interceptors.AuthorizationInterceptor
import com.setesh.data.network.interceptors.NetworkConnectivityInterceptor
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

private const val BASE_URL = "https://api.unsplash.com/"

object ApiProvider {

    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        networkConnectivityInterceptor: NetworkConnectivityInterceptor,
        authorizationInterceptor: AuthorizationInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(networkConnectivityInterceptor)
            .addInterceptor(authorizationInterceptor)
            .connectTimeout(TIME_CONNECTION, TimeUnit.SECONDS)
            .readTimeout(TIME_CONNECTION, TimeUnit.SECONDS)
            .writeTimeout(TIME_CONNECTION, TimeUnit.SECONDS)
            .build()

    fun provideLoggingInterceptor(isDebug: Boolean) = HttpLoggingInterceptor().apply {
        level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}

private const val TIME_CONNECTION = 10L

package com.setesh.data.network.di

import com.setesh.data.BuildConfig
import com.setesh.data.network.ApiProvider
import com.setesh.data.network.interceptors.AuthorizationInterceptor
import com.setesh.data.network.interceptors.NetworkConnectivityInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val apiModule = module {
    single { ApiProvider.provideApi(retrofit = get()) }
    single {
        ApiProvider.provideOkHttpClient(
            loggingInterceptor = get(),
            networkConnectivityInterceptor = get(),
            authorizationInterceptor = get(),
        )
    }
    single { ApiProvider.provideLoggingInterceptor(BuildConfig.DEBUG) }
    single { ApiProvider.provideRetrofit(okHttpClient = get()) }
    single { NetworkConnectivityInterceptor(androidApplication()) }
    single { AuthorizationInterceptor() }
}
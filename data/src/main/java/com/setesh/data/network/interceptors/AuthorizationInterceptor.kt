package com.setesh.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

private const val CLIENT_ID = "Authorization"
private const val ACCESS_KEY = "Client-ID LTptsFwBnV7HD_nmmDDk4rCHqmISqmodhtNP9QCv3cY"

class AuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().header(CLIENT_ID, ACCESS_KEY).build()
        return chain.proceed(request)
    }
}
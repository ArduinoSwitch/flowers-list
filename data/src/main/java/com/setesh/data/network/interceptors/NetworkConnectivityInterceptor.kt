package com.setesh.data.network.interceptors

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.setesh.commons.network.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkConnectivityInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) throw NoConnectivityException()

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkCapabilities =
            connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
        return (
                networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true ||
                        networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
                )
    }
}

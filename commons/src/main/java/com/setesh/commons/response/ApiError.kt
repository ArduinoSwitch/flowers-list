package com.setesh.commons.response

import android.accounts.NetworkErrorException
import com.setesh.commons.network.NoConnectivityException
import timber.log.Timber
import kotlin.coroutines.cancellation.CancellationException

enum class GenericApiError { NoInternet, Network, Server, Unknown }

enum class UiApiError { NoInternet, Generic }

fun GenericApiError.toBasicUi() = when (this) {
    GenericApiError.NoInternet -> UiApiError.NoInternet
    GenericApiError.Network, GenericApiError.Server, GenericApiError.Unknown -> UiApiError.Generic
}

object ApiErrorHandling {
    suspend fun <T> run(block: suspend () -> T): MyResult<T, GenericApiError> = try {
        MyResult.Ok(block())
    } catch (e: Throwable) {
        e.printStackTrace()
        handleGeneric(e)
    }

    private fun handleGeneric(e: Throwable) = when (e) {
        is CancellationException -> throw e
        is NoConnectivityException -> MyResult.Err(GenericApiError.NoInternet)
        is NetworkErrorException -> MyResult.Err(GenericApiError.Network)
        else -> MyResult.Err(GenericApiError.Unknown)
    }
}

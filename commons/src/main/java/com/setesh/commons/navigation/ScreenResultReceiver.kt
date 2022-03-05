package com.setesh.commons.navigation

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.setesh.commons.utils.LogConstants
import timber.log.Timber

interface ScreenResultReceiver {
    val screenResultKeys: List<String>
    fun onResult(key: String, result: ScreenResult)
}

interface ScreenResult : Parcelable

fun Fragment.delegateDialogResults(receiver: ScreenResultReceiver) {
    findNavController().currentBackStackEntry?.savedStateHandle?.let { savedStateHandle ->
        receiver.screenResultKeys.forEach { resultKey ->
            savedStateHandle.getLiveData<ScreenResult>(resultKey).observe(viewLifecycleOwner) {
                if (it != null) {
                    savedStateHandle.set(resultKey, null)
                    receiver.onResult(resultKey, it)
                }
            }
        }
    }
}

fun Fragment.finishWithResult(key: String, result: ScreenResult) {
    try {
        val fragmentActivity = activity as? ScreenResultReceiver
        if (fragmentActivity?.screenResultKeys?.contains(key) == true) {
            navigateUp()
            fragmentActivity.onResult(key, result)
        } else {
            val savedStateHandle = findNavController().previousBackStackEntry?.savedStateHandle
            navigateUp()
            savedStateHandle!!.set(key, result)
        }
    } catch (e: Exception) {
        Timber.tag(LogConstants.SendResultErrorTag).w("Sending result failed: $e")
    }
}

private fun Fragment.navigateUp() {
    activity?.let { activity ->
        if (activity is NavHostHolder) {
            Navigation.findNavController(activity, activity.navHostId).navigateUp()
        } else {
            throw IllegalStateException("The activity should implement NavHostHolder")
        }
    }
}

interface NavHostHolder {
    val navHostId: Int
}

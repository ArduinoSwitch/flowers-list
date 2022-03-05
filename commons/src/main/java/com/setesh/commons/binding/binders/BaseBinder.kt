package com.setesh.commons.binding.binders

import androidx.lifecycle.LifecycleOwner
import com.setesh.commons.ui.utils.observeFromOwner
import kotlinx.coroutines.flow.Flow

interface BaseBinder {
    val lifecycleOwnerProvider: () -> LifecycleOwner
}

inline fun <T> BaseBinder.observe(flow: Flow<T>, crossinline observer: (T) -> Unit) {
    lifecycleOwnerProvider().observeFromOwner(flow, observer)
}

package com.setesh.commons.binding.binders

import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.setesh.commons.binding.efficientText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged

interface TextViewBinders : BaseBinder {
    fun TextView.bind(text: Flow<CharSequence?>) {
        observe(text) { newText ->
            efficientText(newText)
        }
    }

    fun TextView.bindTwoWay(text: MutableStateFlow<String>) {
        bind(text)
        sendTextUpdates(text)
    }

    @Suppress("INAPPLICABLE_JVM_NAME")
    @JvmName("bindTextRes")
    fun TextView.bind(textRes: Flow<Int?>) {
        observe(textRes.distinctUntilChanged()) { newTextRes ->
            text = if (newTextRes == null) "" else resources.getString(newTextRes)
        }
    }
}

private fun TextView.sendTextUpdates(text: MutableStateFlow<in String>) {
    doOnTextChanged { newText, _, _, _ ->
        text.value = newText.toString()
    }
}

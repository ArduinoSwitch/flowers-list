package com.setesh.commons.binding.binders

import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.setesh.commons.binding.efficientText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

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
}

private fun TextView.sendTextUpdates(text: MutableStateFlow<in String>) {
    doOnTextChanged { newText, _, _, _ ->
        text.value = newText.toString()
    }
}

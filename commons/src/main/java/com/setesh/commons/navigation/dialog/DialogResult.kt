package com.setesh.commons.navigation.dialog

import com.setesh.commons.navigation.ScreenResult
import kotlinx.parcelize.Parcelize

abstract class DialogResult : ScreenResult {

    @Parcelize
    object Positive : DialogResult()

    @Parcelize
    data class Binary(
        val isPositive: Boolean
    ) : DialogResult()

    enum class DialogButtons { Positive, Negative, Neutral }
}

package com.setesh.flowers.feature.onboarding.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TabUiModel(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int,
)

package com.setesh.flowers.feature.onboarding.ui

import com.setesh.commons.di.FrontDispatchers
import com.setesh.commons.navigation.Navigator
import com.setesh.commons.ui.BaseViewModel
import com.setesh.flowers.R

class OnBoardingViewModel(
    dispatchers: FrontDispatchers,
    private val navigator: Navigator,
): BaseViewModel(dispatchers) {
    val tabUiList = listOf(
            TabUiModel(
                R.string.on_boarding_welcome_title,
                R.string.on_boarding_welcome_body,
                R.drawable.local_florist_black_24dp
            ),
            TabUiModel(R.string.on_boarding_introduce_title,
                R.string.on_boarding_introduce_body,
                R.drawable.local_florist_black_24dp
            ),
            TabUiModel(R.string.on_boarding_flower_detail_title,
                R.string.on_boarding_flower_detail_body,
                R.drawable.local_florist_black_24dp
            ),
        )
}
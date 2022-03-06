package com.setesh.flowers.feature.onboarding.ui

import com.setesh.commons.di.FrontDispatchers
import com.setesh.commons.navigation.Navigator
import com.setesh.commons.ui.BaseViewModel
import com.setesh.domain.boarding.UpdateOnBoardStatusUseCase
import com.setesh.domain.boarding.UpdateOnBoardStatusUseCaseT
import com.setesh.flowers.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber

class OnBoardingViewModel(
    dispatchers: FrontDispatchers,
    private val navigator: Navigator,
    private val updateOnBoardStatusUseCase: UpdateOnBoardStatusUseCaseT,
): BaseViewModel(dispatchers) {
    val tabUiList = listOf(
            TabUiModel(
                R.string.on_boarding_welcome_title,
                R.string.on_boarding_welcome_body,
                R.drawable.local_florist_black_24dp
            ),
            TabUiModel(R.string.on_boarding_introduce_title,
                R.string.on_boarding_introduce_body,
                R.drawable.list_view
            ),
            TabUiModel(R.string.on_boarding_flower_detail_title,
                R.string.on_boarding_flower_detail_body,
                R.drawable.detail_view
            ),
        )

    val tabPosition = MutableStateFlow(0)
    val backButtonVisible = tabPosition.map { it != 0 }
    val nextOrFinishText = tabPosition.map {
        Timber.i("$it")
        if (it != 2) R.string.on_boarding_next else R.string.on_boarding_finish
    }

    fun onBackClick() {
        if (tabPosition.value > 0) {
            tabPosition.value = tabPosition.value - 1
        }
    }

    fun onNextClick() {
        if (tabPosition.value == 2) {
            updateOnBoardStatus()
            navigator.goTo(OnBoardingFragmentDirections.navToMain())
        }
        if (tabPosition.value < 2) {
            tabPosition.value = tabPosition.value + 1
        }
    }

    fun onSkipClick() {
        updateOnBoardStatus()
        navigator.goTo(OnBoardingFragmentDirections.navToMain())
    }

    private fun updateOnBoardStatus() {
        scope.launch {
            updateOnBoardStatusUseCase(Unit)
        }
    }
}
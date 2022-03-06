package com.setesh.flowers.feature.splash.ui

import com.setesh.commons.di.FrontDispatchers
import com.setesh.commons.navigation.Navigator
import com.setesh.commons.ui.BaseViewModel
import com.setesh.domain.boarding.GetOnBoardStateUseCase
import com.setesh.domain.boarding.GetOnBoardStateUseCaseT
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

class SplashViewModel(
    dispatchers: FrontDispatchers,
    private val navigator: Navigator,
    getOnBoardStateUseCase: GetOnBoardStateUseCaseT,
): BaseViewModel(dispatchers) {
    init {
        scope.launch {
            delay(3000)
            if (getOnBoardStateUseCase(Unit)) navigator.goTo(SplashFragmentDirections.navToMain())
            else navigator.goTo(SplashFragmentDirections.navToOnBoarding())
        }
    }
}

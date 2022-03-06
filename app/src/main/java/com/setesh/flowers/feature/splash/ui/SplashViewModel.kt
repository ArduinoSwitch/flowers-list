package com.setesh.flowers.feature.splash.ui

import com.setesh.commons.di.FrontDispatchers
import com.setesh.commons.navigation.Navigator
import com.setesh.commons.ui.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    dispatchers: FrontDispatchers,
    private val navigator: Navigator,
): BaseViewModel(dispatchers) {
    init {
        scope.launch {
            delay(5000)
            navigator.goTo(SplashFragmentDirections.navToOnBoarding())
            /*navigator.goTo(SplashFragmentDirections.navToMain())*/
        }
    }
}

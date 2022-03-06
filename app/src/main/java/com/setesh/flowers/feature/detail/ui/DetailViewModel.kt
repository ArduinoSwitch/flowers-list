package com.setesh.flowers.feature.detail.ui

import com.setesh.commons.di.FrontDispatchers
import com.setesh.commons.navigation.Navigator
import com.setesh.commons.ui.BaseViewModel

class DetailViewModel(
    dispatchers: FrontDispatchers,
    private val navigator: Navigator,
): BaseViewModel(dispatchers) {
    fun onBackClick() {
        navigator.back()
    }
}
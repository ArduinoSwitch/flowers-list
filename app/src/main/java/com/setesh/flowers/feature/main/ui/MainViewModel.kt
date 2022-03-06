package com.setesh.flowers.feature.main.ui

import com.setesh.commons.di.FrontDispatchers
import com.setesh.commons.navigation.Navigator
import com.setesh.commons.response.onSuccess
import com.setesh.commons.ui.BaseViewModel
import com.setesh.domain.photos.GetPhotosUseCaseT
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    dispatchers: FrontDispatchers,
    private val getPhotosUseCase: GetPhotosUseCaseT,
    private val navigator: Navigator,
): BaseViewModel(dispatchers) {
    init {
        scope.launch {
            getPhotosUseCase(Unit).onSuccess {
                Timber.i("${it.size}")
            }
        }
    }
}

package com.setesh.flowers.feature.main.ui

import com.setesh.commons.di.FrontDispatchers
import com.setesh.commons.ui.BaseViewModel
import com.setesh.domain.photos.GetPhotosUseCaseT
import kotlinx.coroutines.launch

class MainViewModel(
    dispatchers: FrontDispatchers,
    private val getPhotosUseCase: GetPhotosUseCaseT
): BaseViewModel(dispatchers) {
    init {
        scope.launch {
            getPhotosUseCase(Unit)
        }
    }
}
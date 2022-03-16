package com.setesh.flowers.feature.main.di

import com.setesh.domain.di.FETCH
import com.setesh.domain.di.PHOTO_USE_CASE
import com.setesh.domain.photos.GetPhotosUseCaseT
import com.setesh.flowers.feature.main.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        MainViewModel(
            dispatchers = get(),
            getPhotosUseCase = get(named(PHOTO_USE_CASE)),
            fetchMorePhotosUseCase = get(named(FETCH)),
            navigator = get(),
        )
    }
}
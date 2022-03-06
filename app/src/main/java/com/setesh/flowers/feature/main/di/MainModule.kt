package com.setesh.flowers.feature.main.di

import com.setesh.flowers.feature.main.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        MainViewModel(
            dispatchers = get(),
            getPhotosUseCase = get(),
            navigator = get(),
        )
    }
}
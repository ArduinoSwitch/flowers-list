package com.setesh.flowers.feature.splash.di

import com.setesh.flowers.feature.splash.ui.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel {
        SplashViewModel(
            dispatchers = get(),
            navigator = get(),
            getOnBoardStateUseCase = get(),
        )
    }
}
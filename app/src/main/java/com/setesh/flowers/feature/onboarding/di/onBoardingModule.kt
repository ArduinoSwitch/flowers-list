package com.setesh.flowers.feature.onboarding.di

import com.setesh.domain.di.UPDATE_USE_CASE
import com.setesh.flowers.feature.onboarding.ui.OnBoardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val boardingModule = module {
    viewModel {
        OnBoardingViewModel(
            dispatchers = get(),
            navigator = get(),
            updateOnBoardStatusUseCase = get(named(UPDATE_USE_CASE)),
        )
    }
}
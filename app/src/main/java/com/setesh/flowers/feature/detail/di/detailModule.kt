package com.setesh.flowers.feature.detail.di

import com.setesh.flowers.feature.detail.ui.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    viewModel {
        DetailViewModel(
            dispatchers = get(),
            navigator = get(),
        )
    }
}
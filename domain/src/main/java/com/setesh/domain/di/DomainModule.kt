package com.setesh.domain.di

import com.setesh.domain.photos.GetPhotosUseCase
import com.setesh.domain.photos.GetPhotosUseCaseT
import org.koin.dsl.module

val domainModule = module {
    factory<GetPhotosUseCaseT> { GetPhotosUseCase(get()) }
}
package com.setesh.domain.di

import com.setesh.domain.boarding.GetOnBoardStateUseCase
import com.setesh.domain.boarding.GetOnBoardStateUseCaseT
import com.setesh.domain.boarding.UpdateOnBoardStatusUseCase
import com.setesh.domain.boarding.UpdateOnBoardStatusUseCaseT
import com.setesh.domain.photos.GetPhotosUseCase
import com.setesh.domain.photos.GetPhotosUseCaseT
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PHOTO_USE_CASE = "Photo"
const val UPDATE_USE_CASE = "Update"

val domainModule = module {
    factory<GetPhotosUseCaseT>(named(PHOTO_USE_CASE)) { GetPhotosUseCase(get()) }
    factory<GetOnBoardStateUseCaseT> { GetOnBoardStateUseCase(get()) }
    factory<UpdateOnBoardStatusUseCaseT>(named(UPDATE_USE_CASE)) {
        UpdateOnBoardStatusUseCase(get())
    }
}

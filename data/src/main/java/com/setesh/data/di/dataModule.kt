package com.setesh.data.di

import com.setesh.data.photos.PhotoDataSourceImpl
import com.setesh.data.photos.PhotosRepositoryImpl
import com.setesh.domain.photos.PhotoDataSource
import com.setesh.domain.photos.PhotosRepository
import org.koin.dsl.module

val dataModule = module {
    factory<PhotoDataSource> { PhotoDataSourceImpl(get()) }
    factory<PhotosRepository> {
        PhotosRepositoryImpl(
            photosDataSource = get(),
            photoDao = get(),
        )
    }
}

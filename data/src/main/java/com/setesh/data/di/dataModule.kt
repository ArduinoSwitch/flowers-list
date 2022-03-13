package com.setesh.data.di

import com.setesh.data.photos.PhotoDataSourceImpl
import com.setesh.domain.photos.PhotoDataSource
import org.koin.dsl.module

val dataModule = module {
    factory<PhotoDataSource> { PhotoDataSourceImpl(get()) }
}

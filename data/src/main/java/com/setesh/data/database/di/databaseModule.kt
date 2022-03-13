package com.setesh.data.database.di

import com.setesh.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { AppDatabase.getInstance(androidContext()).photoDao() }
}

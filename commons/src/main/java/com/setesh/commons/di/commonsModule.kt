package com.setesh.commons.di

import androidx.datastore.preferences.createDataStore
import com.setesh.commons.navigation.Navigator
import com.setesh.commons.navigation.dialog.DialogData
import com.setesh.commons.navigation.dialog.ui.BinaryDialogViewModel
import com.setesh.commons.navigation.dialog.ui.InformativeDialogViewModel
import com.setesh.commons.preferences.AppPreferencesDataSource
import com.setesh.commons.preferences.AppPreferencesDataSourceImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private const val APP_PREFERENCES_ACCESSOR = "APP_PREFERENCES"

val commonsModule = module {

    single(named(APP_PREFERENCES_ACCESSOR)) {
        androidContext().createDataStore(AppPreferencesDataSourceImpl.PREF_NAME)
    }

    factory<AppPreferencesDataSource> {
        AppPreferencesDataSourceImpl(
            dispatchers = get(),
            dataStore = get(named(APP_PREFERENCES_ACCESSOR)),
        )
    }

    single { Navigator() }
    viewModel { InformativeDialogViewModel(
        dispatchers = get(),
    ) }
    viewModel { (data: DialogData.Binary) ->
        BinaryDialogViewModel(
            dispatchers = get(),
            data,
        )
    }

    single(named(DiConstants.Dispatchers.UI)) { Dispatchers.Main.immediate as CoroutineDispatcher }
    single(named(DiConstants.Dispatchers.CPU)) { Dispatchers.Default }
    single(named(DiConstants.Dispatchers.IO)) { Dispatchers.IO }
    single {
        AppDispatchers(
            get(named(DiConstants.Dispatchers.UI)),
            get(named(DiConstants.Dispatchers.CPU)),
            get(named(DiConstants.Dispatchers.IO)),
        )
    } bind FrontDispatchers::class bind DomainDispatcher::class bind BackDispatchers::class
}

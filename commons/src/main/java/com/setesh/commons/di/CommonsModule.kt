package com.setesh.commons.di

import com.setesh.commons.navigation.Navigator
import com.setesh.commons.navigation.dialog.DialogData
import com.setesh.commons.navigation.dialog.ui.BinaryDialogViewModel
import com.setesh.commons.navigation.dialog.ui.InformativeDialogViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val commonsModule = module {

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

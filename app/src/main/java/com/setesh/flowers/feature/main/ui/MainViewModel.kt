package com.setesh.flowers.feature.main.ui

import com.setesh.commons.di.FrontDispatchers
import com.setesh.commons.navigation.Navigator
import com.setesh.commons.navigation.dialog.DialogData
import com.setesh.commons.response.onFailure
import com.setesh.commons.response.onSuccess
import com.setesh.commons.ui.BaseViewModel
import com.setesh.domain.photos.GetPhotosUseCaseT
import com.setesh.domain.photos.PhotoModel
import com.setesh.flowers.R
import com.setesh.flowers.feature.detail.ui.DetailArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    dispatchers: FrontDispatchers,
    private val getPhotosUseCase: GetPhotosUseCaseT,
    private val navigator: Navigator,
): BaseViewModel(dispatchers) {

    val flowersUiList = MutableStateFlow(emptyList<PhotoModel>())

    init {
        scope.launch {
            getPhotosUseCase(Unit).onSuccess {
                Timber.i("$it")
                flowersUiList.value = it
            }.onFailure {
                navigator.openDialog(DialogData.Informative(R.string.main_title, R.string.main_title))
            }
        }
    }

    fun onPhotoClick(detail: DetailArgs) {
        navigator.goTo(MainFragmentDirections.navToDetail(detail))
    }
}

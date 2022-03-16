package com.setesh.flowers.feature.main.ui

import com.setesh.commons.di.FrontDispatchers
import com.setesh.commons.navigation.Navigator
import com.setesh.commons.navigation.ScreenResult
import com.setesh.commons.navigation.ScreenResultReceiver
import com.setesh.commons.navigation.dialog.DialogData
import com.setesh.commons.navigation.dialog.DialogResult
import com.setesh.commons.response.UiApiError
import com.setesh.commons.response.onFailure
import com.setesh.commons.ui.BaseViewModel
import com.setesh.domain.photos.FetchMorePhotosUseCaseT
import com.setesh.domain.photos.GetPhotosUseCaseT
import com.setesh.flowers.R
import com.setesh.flowers.feature.detail.ui.DetailArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private const val TRY_AGAIN = "try_again"

class MainViewModel(
    dispatchers: FrontDispatchers,
    private val getPhotosUseCase: GetPhotosUseCaseT,
    private val fetchMorePhotosUseCase: FetchMorePhotosUseCaseT,
    private val navigator: Navigator,
): BaseViewModel(dispatchers), ScreenResultReceiver {

    val flowersUiList = getPhotosUseCase.invoke(Unit)
    val isLoading = MutableStateFlow(false)

    init {
        loadData()
    }

    fun onPhotoClick(detail: DetailArgs) {
        navigator.goTo(MainFragmentDirections.navToDetail(detail))
    }

    private fun loadData() {
        scope.launch {
            isLoading.value = true
            fetchMorePhotosUseCase(Unit)
                .onFailure {
                    it.handleUiApiError()
                }
            isLoading.value = false
        }
    }

    private fun UiApiError.handleUiApiError() = when(this) {
        UiApiError.NoInternet -> navigator.openDialog(
            DialogData.Informative(
                R.string.dialog_no_connection_title,
                R.string.dialog_no_connection_description
            )
        )
        UiApiError.Generic -> navigator.openDialog(
            DialogData.Binary(
                title = R.string.dialog_generic_error_title,
                description = R.string.dialog_generic_error_description,
                positiveButton = R.string.dialog_generic_error_positive,
                resultKey = TRY_AGAIN,
            )
        )
    }

    override val screenResultKeys: List<String> = listOf(TRY_AGAIN)

    override fun onResult(key: String, result: ScreenResult) {
        when (key) {
            TRY_AGAIN -> {
                result as DialogResult.Binary
                if (result.isPositive) loadData()
            }
        }
    }
}

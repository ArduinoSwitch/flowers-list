package com.setesh.commons.navigation.dialog.ui

import com.setesh.commons.di.FrontDispatchers
import com.setesh.commons.navigation.ScreenResult
import com.setesh.commons.navigation.dialog.DialogWithResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class ResultDialogViewModel<Result : ScreenResult, Data : DialogWithResult<Result>>(
    dispatchers: FrontDispatchers,
    val data: Data,
) : InformativeDialogViewModel(dispatchers) {
    private val _resultEvent = MutableSharedFlow<Pair<String, ScreenResult>>()
    val resultEvent = _resultEvent.asSharedFlow()

    protected fun sendResult(result: Result) = scope.launch {
        _resultEvent.emit(data.resultKey to result)
    }
}

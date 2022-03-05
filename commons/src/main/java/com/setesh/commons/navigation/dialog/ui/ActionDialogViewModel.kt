package com.setesh.commons.navigation.dialog.ui

import com.setesh.commons.di.FrontDispatchers
import com.setesh.commons.navigation.ScreenResult
import com.setesh.commons.navigation.dialog.DialogData
import com.setesh.commons.navigation.dialog.DialogResult
import com.setesh.commons.navigation.dialog.DialogWithResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface ActionDialogViewModel {
    val resultEvent: SharedFlow<Pair<String, ScreenResult>>
    val dismiss: Flow<Unit>
    fun onPositive()
    fun onNegative()
}

class BinaryDialogViewModel(
    dispatchers: FrontDispatchers,
    data: DialogData.Binary,
) : ResultDialogViewModel<DialogResult.Binary, DialogWithResult<DialogResult.Binary>>(
    dispatchers,
    data
),
    ActionDialogViewModel {

    override fun onPositive() {
        tryEmitAction(DialogResult.DialogButtons.Positive)
    }
    override fun onNegative() {
        tryEmitAction(DialogResult.DialogButtons.Negative)
    }

    private fun tryEmitAction(button: DialogResult.DialogButtons) = sendResult(
        DialogResult.Binary(button == DialogResult.DialogButtons.Positive)
    )
}

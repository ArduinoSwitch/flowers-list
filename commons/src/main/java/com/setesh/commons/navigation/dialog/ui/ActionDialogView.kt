package com.setesh.commons.navigation.dialog.ui

import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.setesh.commons.navigation.dialog.ActionDialog
import com.setesh.commons.navigation.finishWithResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ActionDialogView(
    private val viewModel: ActionDialogViewModel,
    private val dialogData: ActionDialog<*>,
    scope: CoroutineScope,
    private val fragment: DialogFragment,
) : DialogView {

    init {
        scope.launch {
            viewModel.resultEvent.collect { (key, screenResult) ->
                fragment.finishWithResult(key, screenResult)
            }
            viewModel.dismiss.collect { fragment.dismiss() }
        }
    }

    override fun setupWithBuilder(builder: MaterialAlertDialogBuilder) {
        builder.setPositiveButton(dialogData.positiveButton) { _, _ ->
            viewModel.onPositive()
        }
        dialogData.negativeButton.let { stringRes ->
            builder.setNegativeButton(stringRes) { _, _ -> viewModel.onNegative() }
        }
    }
}

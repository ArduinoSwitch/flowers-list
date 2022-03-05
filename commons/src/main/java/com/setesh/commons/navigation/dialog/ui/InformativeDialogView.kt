package com.setesh.commons.navigation.dialog.ui

import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.setesh.commons.navigation.dialog.DialogData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class InformativeDialogView(
    private val viewModel: InformativeDialogViewModel,
    private val dialogData: DialogData.Informative,
    scope: CoroutineScope,
    private val fragment: DialogFragment,
) : DialogView {

    init {
        scope.launch {
            viewModel.dismiss.collect { fragment.dismiss() }
        }
    }

    override fun setupWithBuilder(builder: MaterialAlertDialogBuilder) {
        dialogData.positiveButton?.let { stringRes ->
            builder.setPositiveButton(stringRes) { _, _ -> viewModel.onPositive() }
        }
    }
}

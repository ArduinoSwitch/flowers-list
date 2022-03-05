package com.setesh.commons.navigation.dialog

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import com.setesh.commons.R
import com.setesh.commons.navigation.ScreenResult
import com.setesh.commons.navigation.dialog.ui.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.parcelize.Parcelize
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

sealed class DialogData : Parcelable {
    abstract val title: Int?
    abstract val description: Int?
    abstract val positiveButton: Int?
    open val cancellable = false

    internal abstract fun createView(scope: CoroutineScope, fragment: DialogFragment): DialogView

    @Parcelize
    data class Informative(
        @StringRes override val title: Int?,
        @StringRes override val description: Int?,
        @StringRes override val positiveButton: Int? = R.string.dialog_positive_button,
        override val cancellable: Boolean = true,
    ) : DialogData() {
        override fun createView(scope: CoroutineScope, fragment: DialogFragment) =
            InformativeDialogView(fragment.getViewModel(), this, scope, fragment)
    }

    @Parcelize
    data class Binary(
        @StringRes override val title: Int? = null,
        @StringRes override val description: Int? = null,
        @StringRes override val positiveButton: Int = R.string.dialog_positive_button,
        @StringRes override val negativeButton: Int = R.string.dialog_negative_button,
        override val resultKey: String,
        override val cancellable: Boolean = false,
    ) : ActionDialog<DialogResult.Binary>() {
        override fun getViewModel(fragment: DialogFragment): ActionDialogViewModel =
            fragment.getViewModel<BinaryDialogViewModel> { parametersOf(this) }
    }
}

abstract class DialogWithResult<T : ScreenResult> : DialogData() {
    abstract val resultKey: String
}

abstract class ActionDialog<T : ScreenResult> : DialogWithResult<T>() {
    abstract override val title: Int?
    abstract override val description: Int?
    abstract override val positiveButton: Int
    abstract val negativeButton: Int
    abstract override val resultKey: String
    abstract override val cancellable: Boolean

    override fun createView(scope: CoroutineScope, fragment: DialogFragment) =
        ActionDialogView(getViewModel(fragment), this, scope, fragment)

    protected abstract fun getViewModel(fragment: DialogFragment): ActionDialogViewModel
}

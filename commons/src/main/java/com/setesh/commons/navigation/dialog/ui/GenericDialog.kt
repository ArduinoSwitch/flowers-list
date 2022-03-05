package com.setesh.commons.navigation.dialog.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class GenericDialog : DialogFragment() {
    private val args: GenericDialogArgs by navArgs()

    private var viewScope: CoroutineScope? = null

    private val dispatchers = Dispatchers.Main.immediate

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = with(args.data) {
        viewScope?.cancel()
        val scope = createScope()
        viewScope = scope

        MaterialAlertDialogBuilder(requireContext(), theme)
            .setTitle(title?.let { getString(it) })
            .setMessage(description?.let { getString(it) })
            .setCancelable(cancellable)
            .apply {
                args.data.createView(scope, this@GenericDialog).setupWithBuilder(this)
            }.create()
    }

    private fun createScope() = CoroutineScope(SupervisorJob() + dispatchers)

    override fun onStart() {
        super.onStart()
        dialog?.setCanceledOnTouchOutside(args.data.cancellable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewScope?.cancel()
        viewScope = null
    }
}

interface DialogView {
    fun setupWithBuilder(builder: MaterialAlertDialogBuilder)
}

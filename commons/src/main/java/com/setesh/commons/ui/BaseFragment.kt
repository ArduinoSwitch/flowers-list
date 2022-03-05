package com.setesh.commons.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.setesh.commons.binding.binders.DataBinder
import com.setesh.commons.navigation.ScreenResultReceiver
import com.setesh.commons.navigation.delegateDialogResults

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout), DataBinder {
    override val lifecycleOwnerProvider = { viewLifecycleOwner }

    protected abstract val viewModel: BaseViewModel
    open val additionalScreenResultReceivers: List<ScreenResultReceiver> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        setupScreenResultDelegating()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setupScreenResultDelegating() {
        (listOf(viewModel as? ScreenResultReceiver) + additionalScreenResultReceivers)
            .filterNotNull()
            .toSet()
            .forEach { delegateDialogResults(it) }
    }

    open fun isFinishing(): Boolean = activity?.isFinishing ?: true
    open fun onBackPressed(): Boolean = false
}

package com.setesh.flowers.feature.main.ui

import android.os.Bundle
import android.view.View
import com.setesh.commons.binding.viewBinding
import com.setesh.commons.ui.BaseFragment
import com.setesh.flowers.R
import com.setesh.flowers.databinding.MainFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: BaseFragment(R.layout.main_fragment) {

    private val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)
    override val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bind()
    }

    private fun MainFragmentBinding.bind() {}

}
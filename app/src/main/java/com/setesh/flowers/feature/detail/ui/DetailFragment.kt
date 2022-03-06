package com.setesh.flowers.feature.detail.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import coil.load
import com.setesh.commons.binding.viewBinding
import com.setesh.commons.ui.BaseFragment
import com.setesh.flowers.R
import com.setesh.flowers.databinding.DetailFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment: BaseFragment(R.layout.detail_fragment) {
    private val args: DetailFragmentArgs by navArgs()
    private val binding: DetailFragmentBinding by viewBinding(DetailFragmentBinding::bind)
    override val viewModel: DetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bind()
    }

    private fun DetailFragmentBinding.bind() {
        fullImage.load(args.url)
    }
}

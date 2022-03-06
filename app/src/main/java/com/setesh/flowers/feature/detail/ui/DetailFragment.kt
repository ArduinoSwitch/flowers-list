package com.setesh.flowers.feature.detail.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.setesh.commons.binding.onClick
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
        fullImage.load(args.detail.fullUrl) {
            crossfade(true)
            placeholder(R.drawable.local_florist_black_24dp)
            transformations(RoundedCornersTransformation())
        }
        description.text = args.detail.description
        size.text = getString(R.string.detail_size_placeholder, args.detail.width, args.detail.height)
        likes.text = args.detail.likes.toString()
        back.onClick { viewModel.onBackClick() }
    }
}

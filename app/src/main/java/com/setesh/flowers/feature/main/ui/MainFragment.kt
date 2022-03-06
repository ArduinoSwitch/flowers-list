package com.setesh.flowers.feature.main.ui

import android.os.Bundle
import android.view.View
import coil.load
import coil.transform.RoundedCornersTransformation
import com.setesh.commons.binding.list.GenericListAdapter
import com.setesh.commons.binding.onClick
import com.setesh.commons.binding.viewBinding
import com.setesh.commons.ui.BaseFragment
import com.setesh.domain.photos.PhotoModel
import com.setesh.flowers.R
import com.setesh.flowers.databinding.FlowerItemBinding
import com.setesh.flowers.databinding.MainFragmentBinding
import com.setesh.flowers.feature.detail.ui.DetailArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainFragment: BaseFragment(R.layout.main_fragment) {

    private val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)
    override val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bind()
    }

    private fun MainFragmentBinding.bind() {
        progress.bindIsVisible(viewModel.isLoading)
        flowersRecyclerView.bindGrid(viewModel.flowersUiList, getFlowersAdapter(), 2)
        flowersRecyclerView
            .setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                Timber.i("$oldScrollY")
                Timber.i("$oldScrollX")
                Timber.i("$scrollY")
                Timber.i("$scrollX")
            }
    }

    private fun getFlowersAdapter() = GenericListAdapter(
        getViewBinding = {parent, _ -> parent.viewBinding(FlowerItemBinding::inflate) },
        areItemsSame = {oldItem, newItem -> oldItem == newItem },
        onBind = {item: PhotoModel, binding, _ -> binding.bind(item)}
    )

    private fun FlowerItemBinding.bind(item: PhotoModel) {
        text.text = item.description
        image.load(item.urls.small) {
            crossfade(true)
            placeholder(R.drawable.local_florist_black_24dp)
            transformations(RoundedCornersTransformation())
        }
        root.onClick {
            viewModel.onPhotoClick(
                DetailArgs(
                    description = item.description,
                    width = item.width,
                    height = item.height,
                    likes = item.likes,
                    fullUrl = item.urls.full,
                )
            )
        }
    }

}
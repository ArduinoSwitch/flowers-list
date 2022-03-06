package com.setesh.flowers.feature.onboarding.ui

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.setesh.commons.binding.list.GenericListAdapter
import com.setesh.commons.binding.onClick
import com.setesh.commons.binding.viewBinding
import com.setesh.commons.ui.BaseFragment
import com.setesh.flowers.R
import com.setesh.flowers.databinding.OnBoardingFragmentBinding
import com.setesh.flowers.databinding.PageItemBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnBoardingFragment: BaseFragment(R.layout.on_boarding_fragment) {

    private val binding: OnBoardingFragmentBinding by viewBinding(OnBoardingFragmentBinding::bind)
    override val viewModel: OnBoardingViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bind()
    }

    private fun OnBoardingFragmentBinding.bind() {
        back.bindIsVisible(viewModel.backButtonVisible)
        next.bind(viewModel.nextOrFinishText)
        skip.onClick { viewModel.onSkipClick() }
        back.onClick { viewModel.onBackClick() }
        next.onClick { viewModel.onNextClick() }
        val adapter = getViewPagerAdapter()
        with(viewPager) {
            this.adapter = adapter
            adapter.submitList(viewModel.tabUiList)
        }
        TabLayoutMediator(pagerIndicator, viewPager) { _, _ ->
        }.attach()
        viewPager.bindRetrievePosition(viewModel.tabPosition)
    }

    private fun getViewPagerAdapter() = GenericListAdapter(
        getViewBinding = {parent, _ -> parent.viewBinding(PageItemBinding::inflate) },
        areItemsSame = {new, old -> new == old},
        onBind = ::bind
    )

    private fun bind(
        item: TabUiModel,
        binding: PageItemBinding,
        position: Int,
    ) {
        binding.title.setText(item.title)
        binding.description.setText(item.description)
        binding.icon.setBackgroundResource(item.image)
    }
}

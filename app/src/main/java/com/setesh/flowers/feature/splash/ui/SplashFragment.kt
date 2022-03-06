package com.setesh.flowers.feature.splash.ui

import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import com.setesh.commons.binding.viewBinding
import com.setesh.commons.ui.BaseFragment
import com.setesh.flowers.R
import com.setesh.flowers.databinding.ItemRowBinding
import com.setesh.flowers.databinding.SplashFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

private const val UPPER_LIMIT = 3L
private const val LOWER_LIMIT = 0L
private const val MILLISECONDS_FACTOR = 100

class SplashFragment: BaseFragment(R.layout.splash_fragment) {
    private val binding: SplashFragmentBinding by viewBinding(SplashFragmentBinding::bind)
    override val viewModel: SplashViewModel by viewModel()
    private val drawableList = listOf(
        R.drawable.add_photo_alternate_black_24dp,
        R.drawable.collections_black_24dp,
        R.drawable.image_black_24dp,
        R.drawable.landscape_black_24dp,
        R.drawable.local_florist_black_24dp,
        R.drawable.portrait_black_24dp,
        R.drawable.tonality_black_24dp,
        R.drawable.vrpano_black_24dp,
        R.drawable.ic_photo_camera_back_black_24dp,
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bind()
    }

    private fun SplashFragmentBinding.bind() {
        rowOne.columnOne.setBackgroundResource(drawableList.random())
        rowOne.columnTwo.setBackgroundResource(drawableList.random())
        rowOne.columnThree.setBackgroundResource(drawableList.random())

        rowTwo.columnOne.setBackgroundResource(drawableList.random())
        rowTwo.columnTwo.setBackgroundResource(drawableList.random())
        rowTwo.columnThree.setBackgroundResource(drawableList.random())

        rowThree.columnOne.setBackgroundResource(drawableList.random())
        rowThree.columnTwo.setBackgroundResource(drawableList.random())
        rowThree.columnThree.setBackgroundResource(drawableList.random())

        listOf(rowOne, rowTwo, rowThree).forEach {
            it.bindAnimation()
        }
    }

    private fun ItemRowBinding.bindAnimation() {
        val a1 = AnimatorInflater.loadAnimator(requireContext(), R.animator.fade_out)
        val a2 = AnimatorInflater.loadAnimator(requireContext(), R.animator.fade_out)
        val a3 = AnimatorInflater.loadAnimator(requireContext(), R.animator.fade_out)
        val animOne = AnimatorInflater.loadAnimator(requireContext(), R.animator.fade_in)
        val animTwo = AnimatorInflater.loadAnimator(requireContext(), R.animator.fade_in)
        val animThree = AnimatorInflater.loadAnimator(requireContext(), R.animator.fade_in)
        var timeOne = Random.nextLong(LOWER_LIMIT, UPPER_LIMIT) * MILLISECONDS_FACTOR
        var timeTwo = Random.nextLong(LOWER_LIMIT, UPPER_LIMIT) * MILLISECONDS_FACTOR
        var timeThree = Random.nextLong(LOWER_LIMIT, UPPER_LIMIT) * MILLISECONDS_FACTOR
        a1.setTarget(columnOne)
        a2.setTarget(columnTwo)
        a3.setTarget(columnThree)
        a1.startDelay = timeOne
        a2.startDelay = timeTwo
        a3.startDelay = timeThree
        animOne.setTarget(columnOne)
        animTwo.setTarget(columnTwo)
        animThree.setTarget(columnThree)
        animOne.startDelay = timeOne
        animTwo.startDelay = timeTwo
        animThree.startDelay = timeThree
        a1.start()
        a2.start()
        a3.start()
        a1.doOnEnd {
            it.startDelay = timeOne
            columnOne.setBackgroundResource(drawableList.random())
            animOne.start()
        }
        a2.doOnEnd {
            it.startDelay = timeTwo
            columnTwo.setBackgroundResource(drawableList.random())
            animTwo.start()
        }
        a3.doOnEnd {
            it.startDelay = timeThree
            columnThree.setBackgroundResource(drawableList.random())
            animThree.start()
        }
        animOne.doOnEnd {
            timeOne = Random.nextLong(LOWER_LIMIT, UPPER_LIMIT) * MILLISECONDS_FACTOR
            it.startDelay = timeOne

            a1.start()
        }
        animTwo.doOnEnd {
            timeTwo = Random.nextLong(LOWER_LIMIT, UPPER_LIMIT) * MILLISECONDS_FACTOR
            it.startDelay = timeTwo
            a2.start()
        }
        animThree.doOnEnd {
            timeThree = Random.nextLong(LOWER_LIMIT, UPPER_LIMIT) * MILLISECONDS_FACTOR
            it.startDelay = timeThree
            a3.start()
        }
    }
}
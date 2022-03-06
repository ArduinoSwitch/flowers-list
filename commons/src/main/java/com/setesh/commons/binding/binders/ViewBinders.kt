package com.setesh.commons.binding.binders

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.setesh.commons.binding.showIf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface ViewBinders: BaseBinder {
    fun View.bindIsVisible(isVisible: Flow<Boolean>) {
        observe(isVisible) {
            showIf(it)
        }
    }
    fun ViewPager2.bindRetrievePosition(positionFlow: MutableStateFlow<Int>) {
        observe(positionFlow) {
            this.currentItem = it
        }
        registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                positionFlow.value = position
            }
        })
    }
}
package com.setesh.commons.binding.binders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.setesh.commons.binding.list.GenericListAdapter
import kotlinx.coroutines.flow.Flow

interface ListBinders : BaseBinder {
    fun <T> RecyclerView.bind(
        items: Flow<List<T>>,
        adapter: GenericListAdapter<T, *>,
        isVertical: Boolean = true,
        isReverse: Boolean = false,
    ) {
        setup(adapter, isVertical, isReverse)
        observe(items) {
            adapter.submitList(it)
        }
    }
}

private fun RecyclerView.setup(
    adapter: GenericListAdapter<*, *>,
    isVertical: Boolean,
    isReverse: Boolean,
) {
    val orientation = if (isVertical) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL
    this.adapter = adapter
    this.layoutManager = LinearLayoutManager(context, orientation, isReverse)
}

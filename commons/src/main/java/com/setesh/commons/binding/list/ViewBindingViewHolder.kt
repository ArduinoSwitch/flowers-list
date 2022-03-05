package com.setesh.commons.binding.list

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class ViewBindingViewHolder<T, Binding : ViewBinding>(private val binding: Binding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: T,
        onBind: ((T, Binding, position: Int) -> Unit)?,
        position: Int,
    ) {
        if (onBind != null) {
            onBind(item, binding, position)
        }
    }
}

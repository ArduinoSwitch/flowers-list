package com.setesh.commons.binding.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

/**
 * This class is an Adapter to be used with RecyclerView. It is not necessary to create a different
 * adapter for each list, only a new instance of this class.
 *
 *
 * @param getViewType is used to provide the type when we have more than one kind of items.
 * It is a function of the data because we should use sealed classes in the data model to
 * differentiate between types.
 * We can pass the layout of the item, for example, but it could be any integer
 * @param getViewBinding is used to provide the Binding to the layout of each item
 * It is a function of the viewType to distinguish between those, so using the layout as view types
 * can be helpful here, if we have more than one type.
 * Example: `{ parent, _ -> parent.viewBinding(ItemGenericListBinding::inflate) }`
 * @param areItemsSame is generally to compare id's
 * @param areItemContentsEqual compares the values of those items. If we use all properties of a
 * data class we can leave the default implementation, which uses equality.
 * @param onBind is used to bind data to the views of each item, and register listeners
 *
 *
 * Simple usage example:
 * ```
 *  bind(list, viewModel.listItems, getListAdapter())
 *
 *  private fun getListAdapter() = GenericListAdapter<Sth>(
 *      getViewBinding = { parent, _ -> parent.viewBinding(ItemGenericListBinding::inflate) },
 *      areItemsSame = ::areSthTheSame,
 *      onBind = ::onListItemBind,
 *  )
 *  ```
 *  Multiple layouts:
 *  ```
 *  private fun getListAdapter() = GenericListAdapter<Sth>(
 *      getViewLayout = { data -> if (data is A) R.layout.item_a else R.layout.item_b },
 *      getViewBinding = { parent, layout ->
 *          if (layout == R.layout.item_a) parent.viewBinding(ItemABinding::inflate)
 *          else parent.viewBinding(ItemBBinding::inflate)
 *                       },
 *      areItemsSame = ::areSthTheSame,
 *      onBind = { data: String, binding, _ ->
 *          if (binding is ItemGenericListBinding) {
 *              binding.textView.text = data
 *          } else if (binding is ItemGenericListBBinding) {
 *              binding.textView.text = data
 *          }
 *      },
 *  )
 *  ```
 */
class GenericListAdapter<T, Binding : ViewBinding>(
    val getViewType: (item: T) -> Int = { 0 },
    val getViewBinding: (parent: ViewGroup, viewType: Int) -> Binding,
    areItemsSame: (oldItem: T, newItem: T) -> Boolean,
    areItemContentsEqual: (oldItem: T, newItem: T) -> Boolean = { old, new -> old == new },
    private val onBind: ((T, Binding, position: Int) -> Unit)? = null,
) : ListAdapter<T, ViewBindingViewHolder<T, Binding>>
    (DiffCallback(areItemsSame, areItemContentsEqual)) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder<T, Binding> =
        ViewBindingViewHolder((getViewBinding(parent, viewType)))

    override fun getItemViewType(position: Int) = getViewType(getItem(position))

    override fun onBindViewHolder(holder: ViewBindingViewHolder<T, Binding>, position: Int) =
        holder.bind(getItem(position), onBind, position)

    class DiffCallback<T>(
        val sameItems: (oldItem: T, newItem: T) -> Boolean,
        val sameItemContents: (oldItem: T, newItem: T) -> Boolean,
    ) : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) =
            sameItems(oldItem, newItem)
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return sameItemContents(oldItem, newItem)
        }
    }
}

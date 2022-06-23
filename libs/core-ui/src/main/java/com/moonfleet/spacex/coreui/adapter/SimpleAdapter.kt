package com.moonfleet.spacex.coreui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.properties.Delegates

open class SimpleAdapter<T, V : ViewBinding>(private val bindingBuilder: (ViewGroup, Int) -> V,
                            private val bindItem: (Int, V, T) -> View,
                            private val onItemClick: (T) -> Unit = { _ -> },
                            private val onItemLongClick: (T) -> Unit = { _ -> }) : RecyclerView.Adapter<SimpleAdapter<T, V>.BaseViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(bindingBuilder(parent, viewType))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        holder.bind(position, item, onItemClick, onItemLongClick)
    }

    open var items: MutableList<T> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(items.lastIndex)
    }

    fun updateItem(itemIndex: Int, newItem: T) {
        items[itemIndex] = newItem
        notifyItemChanged(itemIndex)
    }

    inner class BaseViewHolder(private val binding: V) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, item: T, onClickListener: (T) -> Unit, onLongClickListener: (T) -> Unit) {
            val view = bindItem(position, binding, item)
            view.setOnClickListener {
                onClickListener(item)
            }
            view.setOnLongClickListener{
                onLongClickListener(item)
                true
            }
        }

    }
}
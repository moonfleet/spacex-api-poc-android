package com.moonfleet.spacex.feature.launchregistry.ui

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

object ViewBindings {

    @JvmStatic
    @BindingAdapter("adapter")
    fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun setGone(view: View, gone: Boolean) {
        view.visibility = if(gone) View.GONE else View.VISIBLE
    }

    @JvmStatic
    @BindingAdapter(
        "imageUrl",
        "placeholder",
        requireAll = false
    )
    fun ImageView.bindImageUrl(url: String?, placeholder: Drawable?) {
        if(url != null) {
            Glide.with(this).load(url).let { request ->

                if (placeholder != null) {
                    request.placeholder(placeholder)
                }

                request.into(this)
            }
        } else if(placeholder != null) {
            Glide.with(this).load(placeholder).into(this)
        }

    }

}

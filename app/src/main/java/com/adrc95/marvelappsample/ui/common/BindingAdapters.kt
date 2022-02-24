package com.adrc95.marvelappsample.ui.common

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("visible")
fun View.visible(visible: Boolean?) {
    visible?.let {
        setVisible(it)
    }
}

@BindingAdapter("url")
fun ImageView.bindUrl(url: String?) {
    if (url != null) loadUrl(url)
}

@BindingAdapter("customDivider")
fun RecyclerView.divider(@DrawableRes drawableRes: Int?) {
   if (drawableRes != null) setDivider(drawableRes)
}
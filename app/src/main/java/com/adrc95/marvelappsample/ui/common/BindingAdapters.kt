package com.adrc95.marvelappsample.ui.common

import android.content.res.Configuration
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adrc95.marvelappsample.R

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

@BindingAdapter("srcMode")
fun ImageView.setThemeMode(modeType: ModeType? = ModeType.AUTOMATIC) {
   val resource = when (modeType) {
        ModeType.NIGHT -> R.drawable.ic_light_mode
        ModeType.DAY -> R.drawable.ic_dark_mode
        else -> {
            when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_NO -> R.drawable.ic_dark_mode
                Configuration.UI_MODE_NIGHT_YES -> R.drawable.ic_light_mode
                else -> { R.drawable.ic_menu_square}
            }
        }
    }
    val drawable = ContextCompat.getDrawable(context, resource)
    drawable?.setTint(ContextCompat.getColor(context, R.color.white))
    background = drawable

}
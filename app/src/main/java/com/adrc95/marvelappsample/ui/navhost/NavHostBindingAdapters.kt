package com.adrc95.marvelappsample.ui.navhost

import android.view.View
import androidx.databinding.BindingAdapter
import com.adrc95.marvelappsample.ui.common.ModeType

@BindingAdapter(value = ["observableNavHost", "darkMode"], requireAll = true)
fun View.observableFavoriteMenu(observableNavHostMenuObservable: NavHostMenuObservable?, darkMode: ModeType?) {
    observableNavHostMenuObservable?.let {
        if (darkMode != null) {
            it.darkmode = darkMode
        }
    }
}
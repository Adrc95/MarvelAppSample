package com.adrc95.marvelappsample.ui.detail

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["observableFavoriteMenu", "favoriteCharacter"], requireAll = true)
fun View.observableFavoriteMenu(observableFavoriteMenuObservable: FavoriteMenuObservable?, isFavorite: Boolean ) {
    observableFavoriteMenuObservable?.let {
        it.favorite = isFavorite
        it.enabled = true
    }
}

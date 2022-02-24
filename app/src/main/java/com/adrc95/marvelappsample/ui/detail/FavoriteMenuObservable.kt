package com.adrc95.marvelappsample.ui.detail

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.adrc95.marvelappsample.BR

class FavoriteMenuObservable : BaseObservable() {

    @Bindable
    var favorite: Boolean = false
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.favorite)
        }

    @Bindable
    var enabled: Boolean = false
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.enabled)
        }
}
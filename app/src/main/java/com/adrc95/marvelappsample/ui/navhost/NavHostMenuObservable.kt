package com.adrc95.marvelappsample.ui.navhost

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.adrc95.marvelappsample.BR
import com.adrc95.marvelappsample.ui.common.ModeType

class NavHostMenuObservable : BaseObservable() {

    @Bindable
    var darkmode: ModeType = ModeType.DAY
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.darkmode)
        }
}
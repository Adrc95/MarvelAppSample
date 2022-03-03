package com.adrc95.marvelappsample.ui.navhost

import androidx.appcompat.app.AppCompatDelegate
import com.adrc95.marvelappsample.ui.common.ModeType

fun buildNavHostState() = NavHostState()

class NavHostState() {

    fun onChangeTheme(mode : ModeType?) {
        when (mode) {
         ModeType.NIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
         ModeType.DAY ->  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)
        }
    }

}

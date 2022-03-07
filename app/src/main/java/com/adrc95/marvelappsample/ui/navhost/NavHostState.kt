package com.adrc95.marvelappsample.ui.navhost

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.adrc95.marvelappsample.ui.common.ModeType

fun Activity.buildNavHostState(context: Context = this) = NavHostState(context)

class NavHostState(private val context: Context) {

    fun onChangeTheme(delegate: AppCompatDelegate, mode: ModeType) {
        when (mode) {
            ModeType.NIGHT -> delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
            ModeType.DAY -> delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
            else -> getAutomaticColor()
        }
    }

    private fun getAutomaticColor() {
        when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Configuration.UI_MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

}

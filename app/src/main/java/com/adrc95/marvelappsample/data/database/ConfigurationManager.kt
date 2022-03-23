package com.adrc95.marvelappsample.data.database

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.adrc95.marvelappsample.ui.common.ModeType

open class ConfigurationManager constructor(application: Application) {

    companion object {
        private const val PREF_DARK_MODE = "dark_mode"
    }

    private val darkModePref: SharedPreferences =
        application.getSharedPreferences(application.packageName, Context.MODE_PRIVATE)

    var darkMode: Int
        get() = darkModePref.getInt(PREF_DARK_MODE, ModeType.DAY.value)
        set(darkMode) = darkModePref.edit().putInt(PREF_DARK_MODE, darkMode).apply()
}

package com.adrc95.marvelappsample.data.datasource

import com.adrc95.data.source.LocalConfigurationDataSource
import com.adrc95.marvelappsample.data.database.ConfigurationManager

class PreferencesConfigurationDataSource(private val configurationManager: ConfigurationManager) : LocalConfigurationDataSource {

    override suspend fun getThemeMode(): Int =  configurationManager.darkMode

    override suspend fun setThemeMode(mode: Int) {
        configurationManager.darkMode = mode
    }
}
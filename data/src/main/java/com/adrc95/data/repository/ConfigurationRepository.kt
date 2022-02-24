package com.adrc95.data.repository

import com.adrc95.data.source.LocalConfigurationDataSource

interface ConfigurationRepository {
    suspend fun getThemeMode(): Int
    suspend fun setThemeMode(mode: Int)
}

class ConfigurationRepositoryImpl(private val localConfigurationDataSource: LocalConfigurationDataSource) :
    ConfigurationRepository {

    override suspend fun getThemeMode(): Int = localConfigurationDataSource.getThemeMode()

    override suspend fun setThemeMode(mode: Int) {
        localConfigurationDataSource.setThemeMode(mode)
    }

}
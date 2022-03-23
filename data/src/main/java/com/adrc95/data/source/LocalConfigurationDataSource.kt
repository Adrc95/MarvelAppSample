package com.adrc95.data.source

interface LocalConfigurationDataSource {

    suspend fun getThemeMode(): Int

    suspend fun setThemeMode(mode : Int)

}

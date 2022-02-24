package com.adrc95.data.source

import arrow.core.Either
import com.adrc95.data.exception.Failure
import com.adrc95.domain.Character

interface LocalConfigurationDataSource {

    suspend fun getThemeMode(): Int

    suspend fun setThemeMode(mode : Int)

}
package com.adrc95.usecases

import com.adrc95.data.repository.ConfigurationRepository

class ChangeThemeMode(private val configurationRepository: ConfigurationRepository) {

 suspend fun invoke(mode : Int) = configurationRepository.setThemeMode(mode)
}
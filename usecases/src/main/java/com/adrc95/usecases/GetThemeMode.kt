package com.adrc95.usecases

import com.adrc95.data.repository.ConfigurationRepository

class GetThemeMode(private val configurationRepository: ConfigurationRepository) {

 suspend  fun invoke() = configurationRepository.getThemeMode()

}

package com.adrc95.marvelappsample.di

import com.adrc95.data.source.LocalCharactersDataSource
import com.adrc95.data.source.LocalConfigurationDataSource
import com.adrc95.data.source.RemoteCharactersDataSource
import com.adrc95.marvelappsample.data.database.CharacterDao
import com.adrc95.marvelappsample.data.database.ConfigurationManager
import com.adrc95.marvelappsample.data.datasource.MarvelCharactersDataSource
import com.adrc95.marvelappsample.data.datasource.PreferencesConfigurationDataSource
import com.adrc95.marvelappsample.data.datasource.RoomCharactersDataSource
import com.adrc95.marvelappsample.data.server.APIService
import com.adrc95.marvelappsample.data.server.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesRemoteCharacterDataSource(api : APIService<CharacterService>): RemoteCharactersDataSource =
        MarvelCharactersDataSource(api)

    @Provides
    @Singleton
    fun providesLocalCharacterDataSource(dao: CharacterDao): LocalCharactersDataSource =
        RoomCharactersDataSource(dao)

    @Provides
    @Singleton
    fun providesLocalConfigurationDataSource(configurationManager: ConfigurationManager): LocalConfigurationDataSource =
        PreferencesConfigurationDataSource(configurationManager)
}
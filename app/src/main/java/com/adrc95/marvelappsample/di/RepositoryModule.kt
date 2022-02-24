package com.adrc95.marvelappsample.di

import com.adrc95.data.repository.CharactersRepository
import com.adrc95.data.repository.CharactersRepositoryImpl
import com.adrc95.data.repository.ConfigurationRepository
import com.adrc95.data.repository.ConfigurationRepositoryImpl
import com.adrc95.data.source.LocalCharactersDataSource
import com.adrc95.data.source.LocalConfigurationDataSource
import com.adrc95.data.source.RemoteCharactersDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesCharactersRepository(remoteCharactersDataSource: RemoteCharactersDataSource,
    localCharactersDataSource: LocalCharactersDataSource): CharactersRepository =
        CharactersRepositoryImpl(remoteCharactersDataSource, localCharactersDataSource)

    @Provides
    @Singleton
    fun providesConfigurationRepository(localConfigurationDataSource : LocalConfigurationDataSource)
    : ConfigurationRepository = ConfigurationRepositoryImpl(localConfigurationDataSource)
}
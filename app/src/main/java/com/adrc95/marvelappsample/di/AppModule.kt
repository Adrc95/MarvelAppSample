package com.adrc95.marvelappsample.di

import android.app.Application
import android.content.Context
import com.adrc95.marvelappsample.data.database.ConfigurationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAppContext(application: Application): Context = application

    @Provides
    @Singleton
    fun providesConfigurationManager(application: Application) : ConfigurationManager =
        ConfigurationManager(application)
}
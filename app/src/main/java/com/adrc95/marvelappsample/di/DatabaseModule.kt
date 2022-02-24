package com.adrc95.marvelappsample.di

import android.app.Application
import com.adrc95.marvelappsample.data.database.CharacterDao
import com.adrc95.marvelappsample.data.database.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): CharacterDatabase = CharacterDatabase.build(application)

    @Provides
    @Singleton
    fun providesCharacterDao(database: CharacterDatabase): CharacterDao = database.characterDao()

}
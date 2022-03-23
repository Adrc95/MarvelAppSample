package com.adrc95.marvelappsample.di

import com.adrc95.data.repository.CharactersRepository
import com.adrc95.data.repository.ConfigurationRepository
import com.adrc95.usecases.ChangeThemeMode
import com.adrc95.usecases.FavoriteCharacter
import com.adrc95.usecases.GetCharacter
import com.adrc95.usecases.GetCharacters
import com.adrc95.usecases.GetFavoriteCharacters
import com.adrc95.usecases.GetThemeMode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun providesGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharacters =
        GetCharacters(charactersRepository)

    @Provides
    fun providesGetCharacterUseCase(charactersRepository: CharactersRepository): GetCharacter =
        GetCharacter(charactersRepository)

    @Provides
    fun providesGetFavoriteCharactersUseCase(charactersRepository: CharactersRepository): GetFavoriteCharacters =
        GetFavoriteCharacters(charactersRepository)

    @Provides
    fun providesFavoriteCharacter(charactersRepository: CharactersRepository): FavoriteCharacter =
        FavoriteCharacter(charactersRepository)

    @Provides
    fun providesGetThemeMode(localConfigurationRepository: ConfigurationRepository): GetThemeMode =
        GetThemeMode(localConfigurationRepository)

    @Provides
    fun providesChangeThemeMode(localConfigurationRepository: ConfigurationRepository)
    : ChangeThemeMode = ChangeThemeMode(localConfigurationRepository)
}

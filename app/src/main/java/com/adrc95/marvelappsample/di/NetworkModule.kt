package com.adrc95.marvelappsample.di

import com.adrc95.marvelappsample.BuildConfig
import com.adrc95.marvelappsample.data.server.APIService
import com.adrc95.marvelappsample.data.server.CharacterService
import com.adrc95.marvelappsample.data.server.MarvelKeyInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("marvel")
    fun providesEndpoint(): String = "https://gateway.marvel.com"

    @Provides
    @Named("publicKey")
    fun providesPublicKey(): String = BuildConfig.PUBLIC_KEY

    @Provides
    @Named("privateKey")
    fun providesPrivateKey(): String = BuildConfig.PRIVATE_KEY

    @Provides
    @Singleton
    fun providesHttpLogging(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) httpLoggingInterceptor.level =
            HttpLoggingInterceptor.Level.BODY else httpLoggingInterceptor.level =
            HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    fun providesMarvelKeyInterceptor (@Named("publicKey") publicKey: String, @Named("privateKey") privateKey: String): MarvelKeyInterceptor
    = MarvelKeyInterceptor(publicKey, privateKey)

    @ExperimentalSerializationApi
    @Provides
    @Named("json_factory")
    fun provideJsonFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        return json.asConverterFactory(contentType)
    }

    @Provides
    @Singleton
    fun provideCharacterApiService(
        @Named("marvel") endpoint: String,
        marvelKeyInterceptor: MarvelKeyInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("json_factory") jsonFactory : Converter.Factory
    ): APIService<CharacterService> {
        return APIService(
            CharacterService::class.java,
            endpoint,
            jsonFactory,
            arrayOf(marvelKeyInterceptor, httpLoggingInterceptor)
        )
    }

}
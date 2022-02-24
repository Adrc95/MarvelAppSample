package com.adrc95.marvelappsample.data.server

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(@Query("limit") limit: Int, @Query("offset") offset : Int): CharacterDataWrapper

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: Long): CharacterDataWrapper
}
package com.adrc95.marvelappsample.data.datasource

import arrow.core.Either
import com.adrc95.data.exception.Failure
import com.adrc95.data.source.RemoteCharactersDataSource
import com.adrc95.domain.Character
import com.adrc95.marvelappsample.data.mapper.toDomain
import com.adrc95.marvelappsample.data.server.APIService
import com.adrc95.marvelappsample.data.server.CharacterService

class MarvelCharactersDataSource(private val api: APIService<CharacterService>) :
    RemoteCharactersDataSource {

    override suspend fun getCharacters(limit: Int, offset: Int): Either<Failure, List<Character>> = with(api) {
        execute { service.getCharacters(limit, offset).toDomain() }
    }

    override suspend fun getCharacter(id: Long): Either<Failure, Character>  = with(api) {
        execute { service.getCharacter(id).toDomain().first() }
    }

}

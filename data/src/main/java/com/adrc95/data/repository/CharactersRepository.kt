package com.adrc95.data.repository

import arrow.core.Either
import arrow.core.right
import com.adrc95.data.exception.Failure
import com.adrc95.data.source.LocalCharactersDataSource
import com.adrc95.data.source.RemoteCharactersDataSource
import com.adrc95.domain.Character

interface CharactersRepository {
    suspend fun getCharacters(limit: Int, offset: Int) : Either<Failure, List<Character>>
    suspend fun getCharacter(id : Long) : Either<Failure, Character>
    suspend fun getFavoriteCharacters() : Either<Failure, List<Character>>
    suspend fun updateFavoriteCharacter(id : Long, favorite: Boolean) : Either<Failure, Unit>
}

class CharactersRepositoryImpl(private val remoteCharactersDataSource: RemoteCharactersDataSource,
                               private  val localCharactersDataSource : LocalCharactersDataSource)
    : CharactersRepository {

    override suspend fun getCharacters(limit: Int, offset: Int): Either<Failure, List<Character>> =
        remoteCharactersDataSource.getCharacters(limit, offset)

    override suspend fun getCharacter(id: Long): Either<Failure, Character> =
        localCharactersDataSource.getCharacter(id).fold(
            { remoteCharactersDataSource.getCharacter(id).map { localCharactersDataSource.saveCharacter(it); it } },
            { it.right() })

    override suspend fun getFavoriteCharacters(): Either<Failure, List<Character>>  =
        localCharactersDataSource.getFavoriteCharacters()

    override suspend fun updateFavoriteCharacter(id: Long, favorite: Boolean): Either<Failure, Unit> =
        localCharactersDataSource.updateFavoriteCharacter(id, favorite)

}

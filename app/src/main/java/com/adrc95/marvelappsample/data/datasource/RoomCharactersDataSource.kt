package com.adrc95.marvelappsample.data.datasource

import arrow.core.Either
import arrow.core.right
import com.adrc95.data.exception.Failure
import com.adrc95.data.source.LocalCharactersDataSource
import com.adrc95.data.source.RemoteCharactersDataSource
import com.adrc95.domain.Character
import com.adrc95.marvelappsample.data.database.CharacterDao
import com.adrc95.marvelappsample.data.mapper.toDomain
import com.adrc95.marvelappsample.data.mapper.toEntity

class RoomCharactersDataSource(private val dao : CharacterDao) : LocalCharactersDataSource {

    override suspend fun getFavoriteCharacters(): Either<Failure, List<Character>> =
        try {
            val favorites = dao.loadAllFavoriteCharacters()
            Either.Right(favorites.map { it.toDomain() })
        } catch (ex: Exception) {
            Either.Left(Failure.LocalError)
        }

    override suspend fun getCharacter(id: Long): Either<Failure, Character>  =
        try {
            val character = dao.findCharacterById(id)
            Either.Right(character.toDomain())
        } catch (ex: Exception) {
            Either.Left(Failure.LocalError)
        }

    override suspend fun saveCharacter(character: Character): Either<Failure, Long> {
        val characterId = dao.insert(character.toEntity())
        return if (characterId > 1) {
            Either.Right(characterId)
        } else {
            Either.Left(Failure.LocalError)
        }
    }

    override suspend fun updateFavoriteCharacter(id: Long, favorite: Boolean): Either<Failure, Unit> =
        try {
            val updateFavorite = dao.updateFavoriteCharacter(id, favorite)
            Either.Right(Unit)
        } catch (ex: Exception) {
            Either.Left(Failure.LocalError)
        }
}

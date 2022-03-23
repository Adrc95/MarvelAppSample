package com.adrc95.data.source

import arrow.core.Either
import com.adrc95.data.exception.Failure
import com.adrc95.domain.Character

interface LocalCharactersDataSource {

    suspend fun getFavoriteCharacters(): Either<Failure, List<Character>>

    suspend fun getCharacter(id: Long): Either<Failure, Character>

    suspend fun saveCharacter(character: Character) : Either<Failure, Long>

    suspend fun updateFavoriteCharacter(id: Long, favorite: Boolean) : Either<Failure, Unit>
}

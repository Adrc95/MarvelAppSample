package com.adrc95.data.source

import arrow.core.Either
import com.adrc95.data.exception.Failure
import com.adrc95.domain.Character

interface RemoteCharactersDataSource {

    suspend fun getCharacters(limit: Int, offset: Int): Either<Failure, List<Character>>

    suspend fun getCharacter(id: Long): Either<Failure, Character>
}
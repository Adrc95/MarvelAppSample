package com.adrc95.usecases

import arrow.core.Either
import com.adrc95.data.exception.Failure
import com.adrc95.data.repository.CharactersRepository
import com.adrc95.domain.Character

class GetCharacter(private val charactersRepository: CharactersRepository) :
    UseCase<Character, GetCharacter.Params>() {
    override suspend fun run(params: Params): Either<Failure, Character> =
        charactersRepository.getCharacter(params.id)

    data class Params(val id: Long)
}
package com.adrc95.usecases

import arrow.core.Either
import com.adrc95.data.exception.Failure
import com.adrc95.data.repository.CharactersRepository
import com.adrc95.domain.Character

class GetCharacters(private val charactersRepository: CharactersRepository) :
    UseCase<List<Character>, GetCharacters.Params>() {
    override suspend fun run(params: Params): Either<Failure, List<Character>> =
        charactersRepository.getCharacters(params.limit, params.offset)

    data class Params(val limit: Int, val offset: Int)
}
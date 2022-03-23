package com.adrc95.usecases

import arrow.core.Either
import com.adrc95.data.exception.Failure
import com.adrc95.data.repository.CharactersRepository
import com.adrc95.domain.Character

class GetFavoriteCharacters(private val charactersRepository: CharactersRepository) :
    UseCase<List<Character>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<Character>> =
        charactersRepository.getFavoriteCharacters()

}

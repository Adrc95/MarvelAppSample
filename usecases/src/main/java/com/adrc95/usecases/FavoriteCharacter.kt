package com.adrc95.usecases

import arrow.core.Either
import com.adrc95.data.exception.Failure
import com.adrc95.data.repository.CharactersRepository

class FavoriteCharacter(private val charactersRepository: CharactersRepository) :
    UseCase<Unit, FavoriteCharacter.Params>() {
    override suspend fun run(params: Params): Either<Failure, Unit> =
        charactersRepository.updateFavoriteCharacter(params.id, params.favorite)

    data class Params(val id: Long, val favorite : Boolean)
}
package com.adrc95.marvelappsample.ui.favorite

import com.adrc95.domain.Character

data class FavoriteUiState(
    val loading : Boolean = true,
    val characters : List<Character>? = null,
    val emptyFavorites : Boolean = false,
    val serverError : Boolean = false,
)

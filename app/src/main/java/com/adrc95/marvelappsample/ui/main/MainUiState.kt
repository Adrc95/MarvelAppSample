package com.adrc95.marvelappsample.ui.main

import com.adrc95.domain.Character

data class MainUiState(
    val loading : Boolean = true,
    val characters : List<Character>? = null,
    val enabledSearch : Boolean = false,
    val filterQuery : String? = null,
    val moreCharacters : Boolean = false,
    val serverError : Boolean = false
)

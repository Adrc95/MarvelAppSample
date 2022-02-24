package com.adrc95.marvelappsample.ui.detail

import com.adrc95.domain.Character

data class DetailUiState(
    val loading : Boolean = true,
    val character : Character? = null,
)

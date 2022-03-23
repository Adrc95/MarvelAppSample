package com.adrc95.marvelappsample.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrc95.data.exception.Failure
import com.adrc95.domain.Character
import com.adrc95.usecases.FavoriteCharacter
import com.adrc95.usecases.GetCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle,
                                          private  val getCharacter: GetCharacter,
    private val favoriteCharacter: FavoriteCharacter) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    private val args = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle)

    init {
        getCharacter(args.id)
    }

    private fun getCharacter(id : Long) {
        getCharacter(GetCharacter.Params(id),viewModelScope) {
            it.fold(::onCharacterSuccess,::onCharacterSuccess)
        }
    }

    private fun onCharacterSuccess(character: Character) {
        _uiState.update { DetailUiState(loading = false, character = character) }
    }

    private fun onCharacterSuccess(failure: Failure) {

    }

    fun onFavoriteActionClicked() {
        val character = uiState.value.character
        character?.let {
            favoriteCharacter(FavoriteCharacter.Params(it.id, !it.favorite)){ result ->
                result.fold(::onFavoriteError,::onFavoriteSuccess)
            }
        }
    }

    private fun onFavoriteSuccess(unit: Unit) {
        getCharacter(args.id)
    }

    private fun onFavoriteError(failure: Failure) {

    }

    data class DetailUiState(
        val loading : Boolean = true,
        val character : Character? = null,
    )

}

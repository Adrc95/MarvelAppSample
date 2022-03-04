package com.adrc95.marvelappsample.ui.navhost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrc95.marvelappsample.ui.common.ModeType
import com.adrc95.usecases.ChangeThemeMode
import com.adrc95.usecases.GetThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavHostViewModel @Inject constructor(private val changeThemeMode: ChangeThemeMode,
                                           getThemeMode: GetThemeMode) : ViewModel() {

    private val _uiState = MutableStateFlow(NavHostUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = NavHostUiState(ModeType.toModeType(getThemeMode.invoke()))
        }
    }

    fun onChangeTheme() {
        val modeType = uiState.value.mode
        viewModelScope.launch {
           val mode = if (modeType == ModeType.DAY) ModeType.NIGHT else ModeType.DAY
           changeThemeMode.invoke(mode.value)
           _uiState.update { it.copy(mode = mode)}
        }
    }

    fun changeOldMode(mode: ModeType) {
        _uiState.update { it.copy(oldMode = mode) }
    }

    data class NavHostUiState(
        val mode : ModeType = ModeType.AUTOMATIC,
        val oldMode: ModeType? = null,
    )

}

package com.adrc95.marvelappsample.ui.navhost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrc95.marvelappsample.ui.common.ModeType
import com.adrc95.usecases.ChangeThemeMode
import com.adrc95.usecases.GetThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    fun onChangeTheme(mode : ModeType) {
        viewModelScope.launch {
            changeThemeMode.invoke(mode.value)
            _uiState.value = NavHostUiState(mode)
        }
    }

}

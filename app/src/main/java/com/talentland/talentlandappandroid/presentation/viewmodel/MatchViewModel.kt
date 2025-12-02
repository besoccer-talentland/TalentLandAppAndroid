package com.talentland.talentlandappandroid.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talentland.talentlandappandroid.domain.repository.MatchRepository
import com.talentland.talentlandappandroid.presentation.ui.MatchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel que gestiona el estado y la lógica de negocio para la pantalla de partidos.
 */
@HiltViewModel
class MatchViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MatchUiState(isLoading = true))
    val uiState: StateFlow<MatchUiState> = _uiState.asStateFlow()

    init {
        observeMatches()
        loadInitialMatches()
    }

    private fun observeMatches() {
        repository.getMatches()
            .onEach { result ->
                result.fold(
                    onSuccess = { matches ->
                        _uiState.value = MatchUiState(
                            matches = matches,
                            isLoading = false,
                            error = null
                        )
                    },
                    onFailure = {
                        _uiState.value = MatchUiState(
                            matches = emptyList(),
                            isLoading = false,
                            error = null
                        )
                    }
                )
            }
            .catch {
                _uiState.value = MatchUiState(
                    matches = emptyList(),
                    isLoading = false,
                    error = null
                )
            }
            .launchIn(viewModelScope)
    }

    private fun loadInitialMatches() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            repository.refreshMatches()
        }
    }

    /**
     * Refresca la lista de partidos desde el repositorio.
     */
    fun refreshMatches() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            repository.refreshMatches()
        }
    }
}



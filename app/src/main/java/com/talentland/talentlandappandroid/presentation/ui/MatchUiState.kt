package com.talentland.talentlandappandroid.presentation.ui

import com.talentland.talentlandappandroid.domain.model.Match

/**
 * Representa el estado de la UI para la pantalla de partidos.
 */
data class MatchUiState(
    val matches: List<Match> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)



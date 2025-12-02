package com.talentland.talentlandappandroid.domain.repository

import com.talentland.talentlandappandroid.domain.model.Match
import kotlinx.coroutines.flow.Flow

/**
 * Contrato del repositorio de partidos.
 */
interface MatchRepository {

    /** Flujo reactivo de partidos. */
    fun getMatches(): Flow<Result<List<Match>>>

    /** Fuerza refresco desde la fuente de datos. */
    suspend fun refreshMatches(): Result<Unit>
}



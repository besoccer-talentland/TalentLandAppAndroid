package com.talentland.talentlandappandroid.data.repository

import com.talentland.talentlandappandroid.data.api.MatchApiService
import com.talentland.talentlandappandroid.data.mapper.toDomain
import com.talentland.talentlandappandroid.domain.model.Match
import com.talentland.talentlandappandroid.domain.repository.MatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementación del repositorio de partidos.
 */
@Singleton
class MatchRepositoryImpl @Inject constructor(
    private val apiService: MatchApiService
) : MatchRepository {

    private val _matches = MutableStateFlow<Result<List<Match>>>(Result.success(emptyList()))

    override fun getMatches(): Flow<Result<List<Match>>> = _matches.asStateFlow()

    override suspend fun refreshMatches(): Result<Unit> {
        return try {
            val matchesDto = apiService.getMatches()
            val domainMatches = matchesDto.map { it.toDomain() }
            _matches.value = Result.success(domainMatches)
            Result.success(Unit)
        } catch (e: Exception) {
            val errorResult = Result.failure<List<Match>>(e)
            _matches.value = errorResult
            Result.failure(e)
        }
    }
}



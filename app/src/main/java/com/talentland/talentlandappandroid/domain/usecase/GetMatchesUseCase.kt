package com.talentland.talentlandappandroid.domain.usecase

import com.talentland.talentlandappandroid.domain.model.Match
import com.talentland.talentlandappandroid.domain.repository.MatchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(
    private val repository: MatchRepository
) {
    operator fun invoke(): Flow<Result<List<Match>>> = repository.getMatches()
}



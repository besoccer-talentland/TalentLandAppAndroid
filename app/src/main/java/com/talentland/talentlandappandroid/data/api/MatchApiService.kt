package com.talentland.talentlandappandroid.data.api

import com.talentland.talentlandappandroid.data.api.dto.MatchDto
import retrofit2.http.GET

/**
 * Interfaz Retrofit que define los endpoints de la API de partidos.
 */
interface MatchApiService {

    /** Obtiene la lista de partidos desde el servidor. */
    @GET(ApiConstants.ENDPOINT_MATCHES)
    suspend fun getMatches(): List<MatchDto>
}



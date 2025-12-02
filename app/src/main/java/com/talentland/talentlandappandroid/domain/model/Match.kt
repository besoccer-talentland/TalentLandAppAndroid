package com.talentland.talentlandappandroid.domain.model

/**
 * Representa un partido de fútbol en el dominio de la aplicación.
 */
data class Match(
    val id: String,
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int?,
    val awayScore: Int?,
    val status: MatchStatus,
    val date: String,
    val competition: String
)

enum class MatchStatus {
    SCHEDULED,
    LIVE,
    FINISHED,
    CANCELLED
}



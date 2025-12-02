package com.talentland.talentlandappandroid.data.mapper

import com.talentland.talentlandappandroid.data.api.dto.MatchDto
import com.talentland.talentlandappandroid.domain.model.Match
import com.talentland.talentlandappandroid.domain.model.MatchStatus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

private const val SCORE_SEPARATOR = "-"
private const val DATE_FORMAT = "yyyy-MM-dd HH:mm"
private const val MIN_LIVE_MINUTE = 1

fun MatchDto.toDomain(): Match {
    val (homeScore, awayScore) = parseScore(score)
    val status = inferStatus(homeScore, awayScore, liveMinute)
    val id = generateUniqueId()
    val formattedDate = formatCurrentDate()

    return Match(
        id = id,
        homeTeam = localName.trim(),
        awayTeam = visitorName.trim(),
        homeScore = homeScore,
        awayScore = awayScore,
        localShield = localShield.orEmpty(),
        visitorShield = visitorShield.orEmpty(),
        status = status,
        date = formattedDate,
        liveMinute = liveMinute
    )
}

private fun parseScore(score: String?): Pair<Int?, Int?> {
    if (score.isNullOrBlank()) {
        return Pair(null, null)
    }

    return try {
        val parts = score.split(SCORE_SEPARATOR)
        if (parts.size == 2) {
            val home = parts[0].trim().toIntOrNull()
            val away = parts[1].trim().toIntOrNull()
            Pair(home, away)
        } else {
            Pair(null, null)
        }
    } catch (e: Exception) {
        Pair(null, null)
    }
}

private fun inferStatus(homeScore: Int?, awayScore: Int?, liveMinute: Int?): MatchStatus {
    return when {
        liveMinute != null && liveMinute >= MIN_LIVE_MINUTE -> MatchStatus.LIVE
        homeScore != null && awayScore != null -> MatchStatus.FINISHED
        else -> MatchStatus.SCHEDULED
    }
}

private fun generateUniqueId(): String {
    return UUID.randomUUID().toString()
}

private fun formatCurrentDate(): String {
    val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(Date())
}



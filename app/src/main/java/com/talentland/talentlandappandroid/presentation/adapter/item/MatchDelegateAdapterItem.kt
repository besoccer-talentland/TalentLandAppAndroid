package com.talentland.talentlandappandroid.presentation.adapter.item

import com.talentland.talentlandappandroid.core.CornerStyle
import com.talentland.talentlandappandroid.core.adapter.DelegateAdapterItem
import com.talentland.talentlandappandroid.domain.model.Match

data class MatchDelegateAdapterItem(
    val match: Match,
    override var cornerStyle: CornerStyle = CornerStyle.FULL
) : DelegateAdapterItem() {

    override val id: Any
        get() = match.id

    override val content: Any
        get() = match

    override fun payload(other: Any?): Any? {
        if (other !is MatchDelegateAdapterItem) return null
        
        val oldMatch = this.match
        val newMatch = other.match
        
        val payloads = mutableListOf<String>()
        
        if (oldMatch.homeScore != newMatch.homeScore || oldMatch.awayScore != newMatch.awayScore) {
            payloads.add(PAYLOAD_SCORE_CHANGED)
        }
        if (oldMatch.status != newMatch.status) {
            payloads.add(PAYLOAD_STATUS_CHANGED)
        }
        
        return payloads.ifEmpty { null }
    }

    companion object {
        const val PAYLOAD_SCORE_CHANGED = "score_changed"
        const val PAYLOAD_STATUS_CHANGED = "status_changed"
    }
}

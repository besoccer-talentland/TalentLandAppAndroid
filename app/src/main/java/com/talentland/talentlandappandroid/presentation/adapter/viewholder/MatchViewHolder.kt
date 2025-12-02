package com.talentland.talentlandappandroid.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil3.load
import com.talentland.talentlandappandroid.core.CornerStyle
import com.talentland.talentlandappandroid.core.viewholder.BaseViewHolder
import com.talentland.talentlandappandroid.databinding.ItemMatchBinding
import com.talentland.talentlandappandroid.domain.model.Match

class MatchViewHolder(
    private val binding: ItemMatchBinding
) : BaseViewHolder(binding.root) {

    fun bind(match: Match, cornerStyle: CornerStyle) {
        binding.apply {
            tvLocalName.text = match.homeTeam
            tvVisitorName.text = match.awayTeam
            
            if (match.homeScore != null && match.awayScore != null) {
                tvScore.text = "${match.homeScore} - ${match.awayScore}"
            }
            ivLocalShield.load(match.localShield)
            ivVisitorShield.load(match.visitorShield)
            if (match.liveMinute != null) {
                tvLiveMinute.visibility = View.VISIBLE
                tvLiveMinute.text = "${match.liveMinute}'"
            } else {
                tvLiveMinute.visibility = View.GONE
            }
            
            applyCornerRadius(binding.root, cornerStyle)
        }
    }

    companion object {
        fun create(parent: ViewGroup): MatchViewHolder {
            val binding = ItemMatchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return MatchViewHolder(binding)
        }
    }
}

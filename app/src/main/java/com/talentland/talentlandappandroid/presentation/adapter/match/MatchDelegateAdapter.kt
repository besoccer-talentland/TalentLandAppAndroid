package com.talentland.talentlandappandroid.presentation.adapter.match

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.talentland.talentlandappandroid.core.adapter.DelegateAdapter
import com.talentland.talentlandappandroid.core.adapter.DelegateAdapterItem
import com.talentland.talentlandappandroid.presentation.adapter.item.MatchDelegateAdapterItem
import com.talentland.talentlandappandroid.presentation.adapter.viewholder.MatchViewHolder

/**
 * Adaptador delegado para items de tipo Match.
 * Maneja la creación y binding de ViewHolders para partidos.
 */
class MatchDelegateAdapter : DelegateAdapter() {
    
    companion object {
        const val VIEW_TYPE = 1
    }
    
    override fun getViewType(): Int = VIEW_TYPE
    
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MatchViewHolder.create(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateAdapterItem) {
        require(holder is MatchViewHolder) { "ViewHolder must be MatchViewHolder" }
        require(item is MatchDelegateAdapterItem) { "Item must be MatchDelegateAdapterItem" }
        
        holder.bind(item.match, item.cornerStyle)
    }
    
    override fun isForViewType(item: DelegateAdapterItem): Boolean {
        return item is MatchDelegateAdapterItem
    }
}

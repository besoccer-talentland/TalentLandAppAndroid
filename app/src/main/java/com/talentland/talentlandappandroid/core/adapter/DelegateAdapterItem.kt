package com.talentland.talentlandappandroid.core.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.talentland.talentlandappandroid.core.CornerStyle

/**
 * Representa un item genérico para usar con el patrón de delegate adapters.
 */
abstract class DelegateAdapterItem {
    abstract val id: Any
    abstract val content: Any
    
    open var cornerStyle: CornerStyle = CornerStyle.FULL

    /**
     * Permite definir payloads para actualizaciones parciales del item.
     */
    open fun payload(other: Any?): Any? = null

    override fun equals(other: Any?): Boolean {
        if (other !is DelegateAdapterItem) return false
        return id == other.id && content == other.content && cornerStyle == other.cornerStyle
    }

    override fun hashCode(): Int {
        return id.hashCode() * 31 + content.hashCode()
    }

    class DiffCallback : DiffUtil.ItemCallback<DelegateAdapterItem>() {
        override fun areItemsTheSame(
            oldItem: DelegateAdapterItem,
            newItem: DelegateAdapterItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: DelegateAdapterItem,
            newItem: DelegateAdapterItem
        ): Boolean {
            return newItem.content == oldItem.content
        }

        override fun getChangePayload(
            oldItem: DelegateAdapterItem,
            newItem: DelegateAdapterItem
        ): Any? {
            return oldItem.payload(newItem)
        }
    }
}



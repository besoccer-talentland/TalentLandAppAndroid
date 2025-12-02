package com.talentland.talentlandappandroid.core.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Interfaz base para adaptadores delegados que manejan un tipo específico de item.
 * Cada tipo de item (Match, Header, Footer, etc.) implementa su propio DelegateAdapter.
 */
abstract class DelegateAdapter {
    
    /**
     * Devuelve el view type asociado a este adaptador.
     */
    abstract fun getViewType(): Int
    
    /**
     * Crea un ViewHolder para el tipo de item que maneja este adaptador.
     */
    abstract fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    
    /**
     * Vincula los datos del item al ViewHolder.
     */
    abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateAdapterItem)
    
    /**
     * Verifica si este adaptador puede manejar el item dado.
     */
    abstract fun isForViewType(item: DelegateAdapterItem): Boolean
}



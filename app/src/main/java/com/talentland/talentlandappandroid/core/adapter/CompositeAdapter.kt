package com.talentland.talentlandappandroid.core.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Adaptador que combina múltiples [DelegateAdapter] para crear listas heterogéneas.
 * Utiliza un patrón Builder para agregarlos de forma fluida.
 */
class CompositeAdapter private constructor(
    private val adapters: List<DelegateAdapter>
) : ListAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>(
    DelegateAdapterItem.DiffCallback()
) {

    private val adaptersMap = adapters.associateBy { it.getViewType() }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return findAdapterForItem(item)?.getViewType()
            ?: throw IllegalArgumentException("No adapter found for item: ${item.javaClass.simpleName}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return adaptersMap[viewType]?.onCreateViewHolder(parent)
            ?: throw IllegalArgumentException("No adapter found for view type: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        val adapter = findAdapterForItem(item)
            ?: throw IllegalArgumentException("No adapter found for item: ${item.javaClass.simpleName}")
        adapter.onBindViewHolder(holder, item)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }

        val item = getItem(position)
        val adapter = findAdapterForItem(item)
            ?: throw IllegalArgumentException("No adapter found for item: ${item.javaClass.simpleName}")
        adapter.onBindViewHolder(holder, item)
    }

    private fun findAdapterForItem(item: DelegateAdapterItem): DelegateAdapter? {
        return adapters.firstOrNull { it.isForViewType(item) }
    }

    companion object {
        /**
         * Crea un nuevo Builder para construir un CompositeAdapter.
         */
        operator fun invoke(): Builder = Builder()
    }

    /**
     * Builder para crear instancias de [CompositeAdapter].
     */
    class Builder {
        private val adapters = mutableListOf<DelegateAdapter>()

        /**
         * Agrega un [DelegateAdapter] al composite.
         */
        fun add(adapter: DelegateAdapter): Builder {
            adapters.add(adapter)
            return this
        }

        /**
         * Construye el [CompositeAdapter] con todos los adapters agregados.
         */
        fun build(): CompositeAdapter {
            require(adapters.isNotEmpty()) { "At least one adapter must be added" }
            return CompositeAdapter(adapters)
        }
    }
}



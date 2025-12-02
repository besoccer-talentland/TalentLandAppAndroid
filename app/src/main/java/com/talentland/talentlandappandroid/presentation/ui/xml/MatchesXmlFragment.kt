package com.talentland.talentlandappandroid.presentation.ui.xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.talentland.talentlandappandroid.core.CornerStyle
import com.talentland.talentlandappandroid.core.adapter.CompositeAdapter
import com.talentland.talentlandappandroid.core.util.dpToPx
import com.talentland.talentlandappandroid.databinding.FragmentMatchesBinding
import com.talentland.talentlandappandroid.presentation.adapter.item.MatchDelegateAdapterItem
import com.talentland.talentlandappandroid.presentation.adapter.match.MatchDelegateAdapter
import com.talentland.talentlandappandroid.presentation.ui.MatchUiState
import com.talentland.talentlandappandroid.presentation.viewmodel.MatchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MatchesXmlFragment : Fragment() {

    private var _binding: FragmentMatchesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MatchViewModel by viewModels()
    private lateinit var matchesAdapter: CompositeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWindowInsets()
        setupRecyclerView()
        setupObservers()
        setupRefreshButton()
    }
    
    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val navigationBars = insets.getInsets(WindowInsetsCompat.Type.navigationBars())

            val originalPadding = 16.dpToPx(requireContext())
            binding.recyclerViewMatches.updatePadding(
                left = originalPadding,
                top = originalPadding,
                right = originalPadding,
                bottom = navigationBars.bottom + originalPadding
            )
            
            val layoutParams = binding.fabRefresh.layoutParams as? ViewGroup.MarginLayoutParams
            val originalMargin = 16.dpToPx(requireContext())
            layoutParams?.bottomMargin = navigationBars.bottom + originalMargin
            binding.fabRefresh.layoutParams = layoutParams
            insets
        }
    }

    private fun setupRecyclerView() {
        matchesAdapter = CompositeAdapter()
            .add(MatchDelegateAdapter())
            .build()
            
        binding.recyclerViewMatches.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = matchesAdapter            
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    updateUI(uiState)
                }
            }
        }
    }

    private fun updateUI(uiState: MatchUiState) {
        when {
            uiState.isLoading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.recyclerViewMatches.visibility = View.GONE
                binding.layoutError.visibility = View.GONE
                // El icono ya está configurado en el layout
            }
            uiState.matches.isEmpty() -> {
                binding.progressBar.visibility = View.GONE
                binding.recyclerViewMatches.visibility = View.GONE
                binding.layoutError.visibility = View.VISIBLE
                binding.textViewError.text = "No hay datos disponibles"
            }
            else -> {
                binding.progressBar.visibility = View.GONE
                binding.recyclerViewMatches.visibility = View.VISIBLE
                binding.layoutError.visibility = View.GONE
                val adapterItems = uiState.matches.mapIndexed { index, match ->
                    val cornerStyle = when {
                        uiState.matches.size == 1 -> CornerStyle.FULL
                        index == 0 -> CornerStyle.TOP
                        index == uiState.matches.size - 1 -> CornerStyle.BOTTOM
                        else -> CornerStyle.MIDDLE
                    }
                    MatchDelegateAdapterItem(match, cornerStyle)
                }
                matchesAdapter.submitList(adapterItems)
            }
        }
    }

    private fun setupRefreshButton() {
        binding.fabRefresh.setOnClickListener {
            viewModel.refreshMatches()
        }
        binding.buttonRetry.setOnClickListener {
            viewModel.refreshMatches()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.talentland.talentlandappandroid.presentation.ui.xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.talentland.talentlandappandroid.databinding.FragmentMatchesBinding
import com.talentland.talentlandappandroid.presentation.viewmodel.MatchViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment mínimo que inyecta [MatchViewModel].
 * En un commit posterior se conectarán aquí los adapters y la UI real.
 */
@AndroidEntryPoint
class MatchesXmlFragment : Fragment() {

    private var _binding: FragmentMatchesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MatchViewModel by viewModels()

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
        // Aquí podrías observar viewModel.uiState en el futuro.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



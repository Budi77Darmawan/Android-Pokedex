package com.bd_drmwan.pokedex.presentation.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bd_drmwan.commonextension.extensions.gone
import com.bd_drmwan.commonextension.extensions.toast
import com.bd_drmwan.commonextension.extensions.visible
import com.bd_drmwan.pokedex.core.model.RequestStatus
import com.bd_drmwan.pokedex.databinding.FragmentEvolutionBinding
import com.bd_drmwan.pokedex.presentation.detail.adapter.PokemonEvoListAdapter
import com.bd_drmwan.pokedex.presentation.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EvolutionFragment : Fragment() {
    private var _binding: FragmentEvolutionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private val adapter: PokemonEvoListAdapter by lazy { PokemonEvoListAdapter() }
    private var speciesId: Int? = null

    companion object {
        @JvmStatic
        fun newInstance(speciesId: Int?) =
            EvolutionFragment().apply {
                arguments = Bundle().apply {
                    putInt("SPECIES_ID", speciesId ?: 0)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            speciesId = it.getInt("SPECIES_ID")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvolutionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView() {
        binding.rvPokemonEvolution.apply {
            adapter = this@EvolutionFragment.adapter
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.pokemonSpecies.collect {
                when (it) {
                    is RequestStatus.Loading -> binding.progressCircular.visible()
                    is RequestStatus.Success -> {
                        binding.progressCircular.gone()
                        adapter.setData(it.data?.varieties ?: mutableListOf())
                    }
                    is RequestStatus.Error -> {
                        binding.progressCircular.gone()
                        toast(it.message)
                    }
                    else -> viewModel.getEvolutionPokemon(speciesId ?: 0)
                }
            }
        }
    }
}
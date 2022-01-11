package com.bd_drmwan.pokedex.presentation.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bd_drmwan.commonextension.extensions.capitalize
import com.bd_drmwan.commonextension.extensions.gone
import com.bd_drmwan.commonextension.extensions.loadImage
import com.bd_drmwan.commonextension.extensions.visible
import com.bd_drmwan.pokedex.R
import com.bd_drmwan.pokedex.core.model.PokemonModel
import com.bd_drmwan.pokedex.core.model.imageUri
import com.bd_drmwan.pokedex.core.model.tag
import com.bd_drmwan.pokedex.databinding.FragmentDetailBinding
import com.bd_drmwan.pokedex.presentation.detail.adapter.PokemonStatListAdapter
import com.bd_drmwan.pokedex.presentation.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private val adapter: PokemonStatListAdapter by lazy { PokemonStatListAdapter() }
    private var pokemon: PokemonModel? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemon = it.getParcelable("POKEMON")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvStatistic.apply {
            adapter = this@DetailFragment.adapter
        }
        val filterStat =
            pokemon?.stat?.filterNot {
                it.stat?.name?.contains("-") == true
            } ?: mutableListOf()
        adapter.setData(filterStat)
    }

    private fun setupView() {
        binding.apply {
            tvPokemonId.text = pokemon?.tag().toString()
            tvPokemonName.text = pokemon?.name.toString().capitalize()

            tvPokemonType1.text = pokemon?.type?.getOrNull(0).toString().capitalize()
            if ((pokemon?.type?.size ?: 1) > 1) {
                tvPokemonType2.visible()
                tvPokemonType2.text = pokemon?.type?.getOrNull(1).toString().capitalize()
            } else {
                tvPokemonType2.gone()
            }

            imgPokemon.loadImage(pokemon?.imageUri().toString(), R.color.white)
        }
    }

}
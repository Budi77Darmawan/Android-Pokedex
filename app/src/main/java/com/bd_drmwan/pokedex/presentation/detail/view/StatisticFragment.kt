package com.bd_drmwan.pokedex.presentation.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bd_drmwan.pokedex.core.model.PokemonModel
import com.bd_drmwan.pokedex.databinding.FragmentStatisticBinding
import com.bd_drmwan.pokedex.presentation.detail.adapter.PokemonStatListAdapter

class StatisticFragment : Fragment() {
    private var _binding: FragmentStatisticBinding? = null
    private val binding get() = _binding!!

    private val adapter: PokemonStatListAdapter by lazy { PokemonStatListAdapter() }
    private var pokemon: PokemonModel? = null

    companion object {
        @JvmStatic
        fun newInstance(pokemon: PokemonModel?) =
            StatisticFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("POKEMON", pokemon)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemon = it.getParcelable("POKEMON")
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
        _binding = FragmentStatisticBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvStatistic.apply {
            adapter = this@StatisticFragment.adapter
        }
        val filterStat =
            pokemon?.stat?.filterNot {
                it.stat?.name?.contains("-") == true
            } ?: mutableListOf()
        adapter.setData(filterStat)
    }
}
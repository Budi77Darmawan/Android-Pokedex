package com.bd_drmwan.pokedex.presentation.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bd_drmwan.commonextension.extensions.gone
import com.bd_drmwan.commonextension.extensions.toast
import com.bd_drmwan.commonextension.extensions.visible
import com.bd_drmwan.pokedex.core.model.RequestStatus
import com.bd_drmwan.pokedex.databinding.FragmentHomeBinding
import com.bd_drmwan.pokedex.presentation.home.adapter.PokemonGridAdapter
import com.bd_drmwan.pokedex.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val adapter: PokemonGridAdapter by lazy { PokemonGridAdapter() }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSearchBarListener()
        initObservers()
    }

    private fun initRecyclerView() {
        binding.rvPokemon.apply {
            adapter = this@HomeFragment.adapter
        }
    }

    private fun initSearchBarListener() {
        binding.apply {
            inputSearch.doAfterTextChanged {
                if (it?.isEmpty() == true) iconCancelSearch.gone()
                else iconCancelSearch.visible()
            }
            inputSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
            iconCancelSearch.setOnClickListener {
                inputSearch.text?.clear()
                iconCancelSearch.gone()
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.listPokemon.collect {
                when (it) {
                    is RequestStatus.Loading -> binding.progressCircular.visible()
                    is RequestStatus.Success -> {
                        binding.progressCircular.gone()
                        it.data?.let { data ->
                            adapter.setData(data)
                        }
                    }
                    is RequestStatus.Error -> {
                        binding.progressCircular.gone()
                        toast(it.message)
                    }
                    else -> viewModel.getListPokemon()
                }
            }
        }
    }
}
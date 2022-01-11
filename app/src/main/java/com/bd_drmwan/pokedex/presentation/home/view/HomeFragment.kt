package com.bd_drmwan.pokedex.presentation.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bd_drmwan.commonextension.extensions.gone
import com.bd_drmwan.commonextension.extensions.hideSoftKeyboard
import com.bd_drmwan.commonextension.extensions.toast
import com.bd_drmwan.commonextension.extensions.visible
import com.bd_drmwan.pokedex.R
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
        initScrollListener()
        initObservers()
    }

    private fun initRecyclerView() {
        binding.rvPokemon.apply {
            adapter = this@HomeFragment.adapter
        }

        adapter.onDetailClicked {
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                bundleOf("POKEMON" to it)
            )
        }
    }

    private fun initScrollListener() {
        binding.rvPokemon.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val layoutMgr = recyclerView.layoutManager as? GridLayoutManager
                    val itemCount = layoutMgr?.itemCount ?: 0
                    val lastVisible = layoutMgr?.findLastCompletelyVisibleItemPosition()
                    val isRequest = (itemCount - 3) == lastVisible

                    if (isRequest) viewModel.getNextPageListPokemon()
                }
            }
        })
    }


    private fun initSearchBarListener() {
        binding.apply {
            inputSearch.doAfterTextChanged {
                if (it?.isEmpty() == true) iconCancelSearch.gone()
                else iconCancelSearch.visible()
                viewModel.searchPokemon(it.toString())
            }
            inputSearch.setOnEditorActionListener { view, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    view.clearFocus()
                    hideSoftKeyboard(view)
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

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.listPokemonNextPage.collect {
                when (it) {
                    is RequestStatus.Loading -> {
                        binding.progressCircularBottom.visible()
                        binding.rvPokemon.smoothScrollToPosition(binding.rvPokemon.bottom)
                    }
                    is RequestStatus.Success -> {
                        binding.progressCircularBottom.gone()
                        it.data?.let { data ->
                            adapter.addData(data)
                        }
                    }
                    is RequestStatus.Error -> {
                        binding.progressCircularBottom.gone()
                        toast(it.message)
                    }
                    else -> Unit
                }
            }
        }
    }
}
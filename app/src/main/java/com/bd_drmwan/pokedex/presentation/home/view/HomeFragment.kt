package com.bd_drmwan.pokedex.presentation.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import com.bd_drmwan.commonextension.extensions.gone
import com.bd_drmwan.commonextension.extensions.visible
import com.bd_drmwan.pokedex.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun initRecyclerView() {

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
}
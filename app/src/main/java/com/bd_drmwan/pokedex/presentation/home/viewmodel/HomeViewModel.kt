package com.bd_drmwan.pokedex.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bd_drmwan.pokedex.core.model.PokemonModel
import com.bd_drmwan.pokedex.core.model.RequestStatus
import com.bd_drmwan.pokedex.core.repository.IPokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: IPokemonRepository
) : ViewModel() {

    private val _listPokemon =
        MutableStateFlow<RequestStatus<List<PokemonModel>>?>(null)
    val listPokemon get() = _listPokemon.asStateFlow()
    private val _listPokemonNextPage =
        MutableStateFlow<RequestStatus<List<PokemonModel>>?>(null)
    val listPokemonNextPage get() = _listPokemonNextPage.asStateFlow()

    private var onSearchState = false
    private var page = 1
    private val tempData = mutableListOf<PokemonModel>()

    fun getListPokemon() {
        viewModelScope.launch {
            repository.getListPokemon(page).collect {
                if (page == 1) _listPokemon.emit(it)
                else _listPokemonNextPage.emit(it)
                updateTemporaryData(it)
            }
        }
    }

    fun getNextPageListPokemon() {
        if (listPokemonNextPage.value !is RequestStatus.Loading && !onSearchState) {
            page++
            getListPokemon()
        }
    }

    fun searchPokemon(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                onSearchState = false
                _listPokemon.emit(RequestStatus.Success(tempData))
            } else {
                onSearchState = true
                val data = tempData.filter { it.name.contains(query, true) }
                _listPokemon.emit(RequestStatus.Success(data))
            }
        }
    }

    private fun updateTemporaryData(data: RequestStatus<List<PokemonModel>>?) {
        if (data is RequestStatus.Success) {
            tempData.addAll(data.data ?: listOf())
        }
    }
}
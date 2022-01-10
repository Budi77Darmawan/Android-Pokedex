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

    private var page = 1

    fun getListPokemon() {
        viewModelScope.launch {
            repository.getListPokemon(page).collect {
                if (page == 1) _listPokemon.emit(it)
                else _listPokemonNextPage.emit(it)
            }
        }
    }

    fun getNextPageListPokemon() {
        if (listPokemonNextPage.value is RequestStatus.Success || listPokemonNextPage.value == null) {
            page++
            getListPokemon()
        }
    }
}
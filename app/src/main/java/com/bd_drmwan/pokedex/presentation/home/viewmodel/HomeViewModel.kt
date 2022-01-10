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

    fun getListPokemon() {
        viewModelScope.launch {
            repository.getListPokemon(1).collect {
                _listPokemon.emit(it)
            }
        }
    }
}
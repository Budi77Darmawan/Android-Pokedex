package com.bd_drmwan.pokedex.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bd_drmwan.pokedex.core.model.PokemonSpeciesResponse
import com.bd_drmwan.pokedex.core.model.RequestStatus
import com.bd_drmwan.pokedex.core.repository.IPokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: IPokemonRepository
): ViewModel() {

    private val _pokemonSpecies =
        MutableStateFlow<RequestStatus<PokemonSpeciesResponse>?>(null)
    val pokemonSpecies get() = _pokemonSpecies.asStateFlow()

    fun getEvolutionPokemon(speciesId: Int) {
        viewModelScope.launch {
            repository.getEvolutionPokemon(speciesId).collect {
                _pokemonSpecies.emit(it)
            }
        }
    }
}
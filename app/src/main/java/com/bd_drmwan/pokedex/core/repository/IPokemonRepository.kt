package com.bd_drmwan.pokedex.core.repository

import com.bd_drmwan.pokedex.core.model.PokemonModel
import com.bd_drmwan.pokedex.core.model.PokemonResponse
import com.bd_drmwan.pokedex.core.model.PokemonSpeciesResponse
import com.bd_drmwan.pokedex.core.model.RequestStatus
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    suspend fun getListPokemon(page: Int): Flow<RequestStatus<List<PokemonModel>>>
    suspend fun getDetailPokemon(id: Int): Flow<RequestStatus<PokemonResponse>>
    suspend fun getEvolutionPokemon(speciesId: Int): Flow<RequestStatus<PokemonSpeciesResponse>>
}
package com.bd_drmwan.pokedex.core.services

import com.bd_drmwan.pokedex.core.model.ListPokemonResponse
import com.bd_drmwan.pokedex.core.model.PokemonResponse
import com.bd_drmwan.pokedex.core.model.PokemonSpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): Response<ListPokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int
    ): Response<PokemonResponse>

    @GET("pokemon-species/{speciesId}")
    suspend fun getEvolutionPokemon(
        @Path("speciesId") speciesId: Int
    ): Response<PokemonSpeciesResponse>
}
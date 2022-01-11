package com.bd_drmwan.pokedex.core.repository

import com.bd_drmwan.pokedex.core.data_source.RemoteDataSource
import com.bd_drmwan.pokedex.core.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): IPokemonRepository {
    override suspend fun getListPokemon(page: Int): Flow<RequestStatus<List<PokemonModel>>> {
        return getRemotePokemon(page)
    }

    override suspend fun getEvolutionPokemon(speciesId: Int): Flow<RequestStatus<PokemonSpeciesResponse>> {
        return remoteDataSource.getEvolutionPokemon(speciesId)
    }

    override suspend fun getDetailPokemon(id: Int): Flow<RequestStatus<PokemonResponse>> {
        return remoteDataSource.getDetailPokemonById(id)
    }

    private suspend fun getRemotePokemon(page: Int): Flow<RequestStatus<List<PokemonModel>>> {
        return flow {
            val data = mutableListOf<PokemonModel>()
            remoteDataSource.getListPokemon(page).collect {
                when (it) {
                    is RequestStatus.Loading -> emit(RequestStatus.Loading())
                    is RequestStatus.Success -> {
                        if (it.data?.results.isNullOrEmpty()) {
                            emit(RequestStatus.Success(listOf()))
                        } else {
                            it.data?.results?.forEach { pokemon ->
                                getDetailPokemon(pokemon.id).collect { res ->
                                    if (res is RequestStatus.Success) {
                                        data.add(mapPokemonResponseToModel(res.data))
                                    }
                                }
                            }
                            emit(RequestStatus.Success(data))
                        }
                    }
                    is RequestStatus.Error -> {
                        emit(RequestStatus.Error(it.message, it.errorType))
                    }
                }
            }
        }
    }

    private fun mapPokemonResponseToModel(data: PokemonResponse?): PokemonModel {
        return if (data == null) {
            PokemonModel(0,"", null, null,0)
        } else {
            PokemonModel(
                data.id ?: 0,
                data.name.toString(),
                data.types.map { res -> res.type?.name.toString() }.toMutableList(),
                data.stats.toMutableList(),
                data.species?.id ?: 0
            )
        }
    }
}
package com.bd_drmwan.pokedex.core.repository

import com.bd_drmwan.pokedex.core.data_source.RemoteDataSource
import com.bd_drmwan.pokedex.core.model.ListPokemonResponse
import com.bd_drmwan.pokedex.core.model.PokemonModel
import com.bd_drmwan.pokedex.core.model.PokemonResponse
import com.bd_drmwan.pokedex.core.model.RequestStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): IPokemonRepository {
    override suspend fun getListPokemon(page: Int): Flow<RequestStatus<List<PokemonModel>>> {
        return getRemotePokemon(page)
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
                                val id = pokemon.url?.split("/")
                                    ?.filter { url -> url.isNotBlank() } ?: listOf()
                                data.add(getDetailPokemon(id.last().toInt()))
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

    private suspend fun getDetailPokemon(id: Int): PokemonModel {
        var pokemon = PokemonModel(id,"null", mutableListOf())
        remoteDataSource.getDetailPokemonById(id).collect {
            if (it is RequestStatus.Success) {
                pokemon = PokemonModel(
                    id,
                    it.data?.name.toString(),
                    it.data?.types?.map { data -> data.type?.name.toString() }?.toMutableList()
                )
            }
        }
        return pokemon
    }
}
package com.bd_drmwan.pokedex.core.data_source

import com.bd_drmwan.pokedex.core.model.ListPokemonResponse
import com.bd_drmwan.pokedex.core.model.PokemonResponse
import com.bd_drmwan.pokedex.core.model.PokemonSpeciesResponse
import com.bd_drmwan.pokedex.core.model.RequestStatus
import com.bd_drmwan.pokedex.core.services.PokemonService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: PokemonService
) : BaseDataSource() {

    fun getListPokemon(page: Int): Flow<RequestStatus<ListPokemonResponse>> {
        return flow {
            emit(RequestStatus.Loading())
            try {
                val response = service.getListPokemon(offset = (page-1)*20)
                validateResponse(response,
                    onSuccess = {
                        emit(RequestStatus.Success(it))
                    },
                    onError = {
                        emit(RequestStatus.Error(it))
                    }
                )
            } catch (e: Throwable) {
                validateError(e) { errorMsg, errorType ->
                    emit(RequestStatus.Error(errorMsg, errorType))
                }
            }
        }
    }

    fun getDetailPokemonById(id: Int): Flow<RequestStatus<PokemonResponse>> {
        return flow {
            emit(RequestStatus.Loading())
            try {
                val response = service.getPokemonById(id)
                validateResponse(response,
                    onSuccess = {
                        emit(RequestStatus.Success(it))
                    },
                    onError = {
                        emit(RequestStatus.Error(it))
                    }
                )
            } catch (e: Throwable) {
                validateError(e) { errorMsg, errorType ->
                    emit(RequestStatus.Error(errorMsg, errorType))
                }
            }
        }
    }

    fun getEvolutionPokemon(speciesId: Int): Flow<RequestStatus<PokemonSpeciesResponse>> {
        return flow {
            emit(RequestStatus.Loading())
            try {
                val response = service.getEvolutionPokemon(speciesId)
                validateResponse(response,
                    onSuccess = {
                        emit(RequestStatus.Success(it))
                    },
                    onError = {
                        emit(RequestStatus.Error(it))
                    }
                )
            } catch (e: Throwable) {
                validateError(e) { errorMsg, errorType ->
                    emit(RequestStatus.Error(errorMsg, errorType))
                }
            }
        }
    }
}
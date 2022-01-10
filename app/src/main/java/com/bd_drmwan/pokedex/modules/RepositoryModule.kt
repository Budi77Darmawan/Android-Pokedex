package com.bd_drmwan.pokedex.modules

import com.bd_drmwan.pokedex.core.data_source.RemoteDataSource
import com.bd_drmwan.pokedex.core.repository.IPokemonRepository
import com.bd_drmwan.pokedex.core.repository.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        remoteDataSource: RemoteDataSource
    ): IPokemonRepository {
        return PokemonRepositoryImpl(remoteDataSource)
    }
}
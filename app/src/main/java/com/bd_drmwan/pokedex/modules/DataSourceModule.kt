package com.bd_drmwan.pokedex.modules

import com.bd_drmwan.pokedex.core.data_source.RemoteDataSource
import com.bd_drmwan.pokedex.core.services.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        service: PokemonService
    ): RemoteDataSource {
        return RemoteDataSource(service)
    }
}
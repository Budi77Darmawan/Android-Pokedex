package com.bd_drmwan.pokedex.modules

import com.bd_drmwan.pokedex.BuildConfig.BASE_URL
import com.bd_drmwan.pokedex.core.services.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun providePokemonService(
        client: OkHttpClient
    ): PokemonService =
        NetworkModule.buildRetrofit(BASE_URL, client)
            .create(PokemonService::class.java)

}
package com.bd_drmwan.pokedex.modules

import android.content.Context
import com.bd_drmwan.pokedex.core.services.NetworkException
import com.bd_drmwan.pokedex.core.services.NetworkUtil
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideChuckInterceptor(
        @ApplicationContext mContext: Context
    ) = ChuckInterceptor(mContext)

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun provideNetworkInterceptor(
        @ApplicationContext mContext: Context
    ): Interceptor {
        return Interceptor { chain ->
            val request: Request = chain.request()
            if (!NetworkUtil.isConnected(mContext)) {
                throw NetworkException()
            }
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        chuckInterceptor: ChuckInterceptor,
        networkInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(chuckInterceptor)
            .addInterceptor(networkInterceptor)
            .build()
    }

    fun buildRetrofit(
        baseUrl: String,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
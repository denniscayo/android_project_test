package com.latinos.services.remote.di

import android.content.Context
import com.latinos.services.BuildConfig
import com.latinos.services.remote.manager.NetworkManager
import com.latinos.services.remote.manager.NetworkManagerImpl
import com.latinos.services.remote.services.RestServices
import com.latinos.services.remote.services.charaters.CharacterRemoteDataSource
import com.latinos.services.remote.services.charaters.CharacterRemoteDataSourceImpl
import com.latinos.services.remote.services.charaters.CharactersService
import com.latinos.services.remote.services.charaters.CharactersServiceData
import com.latinos.services.remote.services.charaters.error.CharacterErrorMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServicesModule {
    @Provides
    @Singleton
    fun provideCharactersService(client: OkHttpClient): CharactersService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.URL_API)
            .client(client)
            .build()
            .create(CharactersService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val defaultRequest = chain.request()

                val defaultHttpUrl = defaultRequest.url

                val httpUrl = defaultHttpUrl.newBuilder()
                    .addQueryParameter("apikey", CharactersServiceData.API_KEY)
                    .addQueryParameter("ts", CharactersServiceData.timeStamp)
                    .addQueryParameter("hash", CharactersServiceData.hash())
                    .build()

                val requestBuilder = defaultRequest.newBuilder()
                    .url(httpUrl)

                chain.proceed(requestBuilder.build())
            }
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRestServices(networkManager: NetworkManager): RestServices {
        return RestServices.Default(networkManager)
    }

    @Provides
    @Singleton
    fun providesNetworkManager(@ApplicationContext context: Context): NetworkManager =
        NetworkManagerImpl(context)

    @Provides
    @Singleton
    internal fun provideCharacterRemoteDataSource(
        restServices: RestServices,
        charactersService: CharactersService,
        errorMapper: CharacterErrorMapper,
    ): CharacterRemoteDataSource {
        return CharacterRemoteDataSourceImpl(restServices, charactersService, errorMapper)
    }
}
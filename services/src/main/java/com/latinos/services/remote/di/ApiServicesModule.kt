package com.latinos.services.remote.di

import com.latinos.services.BuildConfig
import com.latinos.services.remote.charaters.CharactersService
import com.latinos.services.remote.charaters.CharactersServiceData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}
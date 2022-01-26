package com.latinos.services.remote.di

import com.latinos.services.BuildConfig
import com.latinos.services.remote.charaters.CharatersService
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
class ApiServicesModule {
    @Provides
    @Singleton
    fun provideCharacterService(client: OkHttpClient): CharatersService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.URL_API)
            .client(client)
            .build()
            .create(CharatersService::class.java)
    }
}
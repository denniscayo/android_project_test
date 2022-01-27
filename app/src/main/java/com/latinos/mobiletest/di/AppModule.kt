package com.latinos.mobiletest.di

import com.google.gson.Gson
import com.latinos.data.error.GlobalErrorMapperImpl
import com.latinos.domain.utils.dispatchers.DefaultDispatcherProvider
import com.latinos.domain.utils.dispatchers.DispatcherProvider
import com.latinos.domain.utils.error.GlobalErrorMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGlobalErrorMapper(
    ): GlobalErrorMapper {
        return GlobalErrorMapperImpl()
    }

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}
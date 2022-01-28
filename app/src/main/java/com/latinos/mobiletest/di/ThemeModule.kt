package com.latinos.mobiletest.di

import com.latinos.data.di.MoshiDefault
import com.latinos.mobiletest.themeloader.ThemeHelper
import com.latinos.mobiletest.themeloader.ThemeHelperImpl
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ThemeModule {
    @Binds
    abstract fun provideThemeHelper(
        themeHelperImpl: ThemeHelperImpl,
    ): ThemeHelper

    companion object {
        @Provides
        @MoshiDefault
        fun provideMoshi(
            builder: Moshi.Builder,
        ): Moshi = builder.build()

        @Provides
        fun provideMoshiBuilder() = Moshi.Builder()
    }
}
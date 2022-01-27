package com.latinos.database.di

import com.latinos.database.MarvelDatabase
import com.latinos.database.dao.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideMovieDao(database: MarvelDatabase): CharacterDao {
        return database.characterDao()
    }
}
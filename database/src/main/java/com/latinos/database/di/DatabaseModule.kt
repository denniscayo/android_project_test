package com.latinos.database.di

import android.content.Context
import androidx.room.Room
import com.latinos.database.MarvelDatabase
import com.latinos.database.dao.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideMovieDao(database: MarvelDatabase): CharacterDao {
        return database.characterDao()
    }

    @Provides
    @Singleton
    fun providesRoomDb(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(context, MarvelDatabase::class.java, "marvel_database")
        .fallbackToDestructiveMigration()
        .build()
}
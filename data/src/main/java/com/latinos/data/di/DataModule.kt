package com.latinos.data.di

import com.latinos.data.character.CharacterRepositoryImpl
import com.latinos.domain.characters.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl,
    ): CharacterRepository
}
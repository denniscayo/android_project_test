package com.latinos.domain.characters.repository

import androidx.paging.PagingData
import com.latinos.domain.characters.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getPaginatedCharacters(): Flow<PagingData<CharacterModel>>
}
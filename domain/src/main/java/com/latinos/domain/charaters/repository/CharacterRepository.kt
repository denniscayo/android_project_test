package com.latinos.domain.charaters.repository

import androidx.paging.PagingData
import com.latinos.domain.charaters.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getPaginatedCharacters(): Flow<PagingData<CharacterModel>>
}
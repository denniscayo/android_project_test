package com.latinos.domain.characters.repository

import androidx.paging.PagingData
import com.latinos.common.utils.either.Either
import com.latinos.domain.characters.model.CharacterDetailModel
import com.latinos.domain.characters.model.CharacterErrorModel
import com.latinos.domain.characters.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getPaginatedCharacters(): Flow<PagingData<CharacterModel>>

    //fun getCharacterById(characterId: String): Flow<Result<out CharacterDetailModel?>>

    fun getCharacterByIdNew(characterId: String): Either<CharacterDetailModel, CharacterErrorModel>
}
package com.latinos.data.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.latinos.common.utils.either.Either
import com.latinos.common.utils.either.map
import com.latinos.data.character.mapper.toCharacterDetailModel
import com.latinos.data.character.mapper.toCharacterEntity
import com.latinos.data.character.mapper.toCharacterErrorModel
import com.latinos.data.character.paging.CharacterPagingSource
import com.latinos.database.dao.CharacterDao
import com.latinos.domain.characters.model.CharacterDetailModel
import com.latinos.domain.characters.model.CharacterErrorModel
import com.latinos.domain.characters.repository.CharacterRepository
import com.latinos.services.remote.services.charaters.CharacterRemoteDataSource
import com.latinos.services.remote.services.charaters.CharactersService
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val charactersService: CharactersService,
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val characterDao: CharacterDao,
) : CharacterRepository {
    override fun getPaginatedCharacters() = Pager(
        PagingConfig(pageSize = 50)
    ) {
        CharacterPagingSource(charactersService)
    }.flow

    override fun getCharacterByIdNew(characterId: String): Either<CharacterDetailModel, CharacterErrorModel> {
        val localData = characterDao.getCharacterById(characterId.toInt())

        return if (localData == null) {
            characterRemoteDataSource
                .getCharacterById(characterId)
                .map(
                    onSuccess = { characterDTO ->
                        characterDao.insertCharacterEntity(characterDTO.toCharacterEntity())
                        characterDTO.toCharacterDetailModel()

                    },
                    onFailure = { it.toCharacterErrorModel() }
                )
        } else {
            Either.Success(localData.toCharacterDetailModel())
        }
    }
}
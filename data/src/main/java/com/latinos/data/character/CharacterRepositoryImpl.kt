package com.latinos.data.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.latinos.data.character.mapper.toCharacterDetailModel
import com.latinos.data.character.mapper.toCharacterEntity
import com.latinos.data.character.paging.CharacterPagingSource
import com.latinos.data.utils.ErrorHandler
import com.latinos.data.utils.NetworkBoundResource
import com.latinos.database.dao.CharacterDao
import com.latinos.domain.characters.model.CharacterDetailModel
import com.latinos.domain.characters.repository.CharacterRepository
import com.latinos.services.remote.charaters.CharactersService
import com.latinos.services.remote.charaters.dto.CharacterDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val charactersService: CharactersService,
    private val characterDao: CharacterDao,
    private val errorHandler: ErrorHandler,
) : CharacterRepository {
    override fun getPaginatedCharacters() = Pager(
        PagingConfig(pageSize = 50)
    ) {
        CharacterPagingSource(charactersService)
    }.flow

    override fun getCharacterById(characterId: String) =
        object : NetworkBoundResource<CharacterDTO, CharacterDetailModel?>(errorHandler) {

            override suspend fun saveRemoteData(response: CharacterDTO) {
                characterDao.insertCharacterEntity(response.data.results[0].toCharacterEntity())
            }

            override fun fetchFromLocal() =
                characterDao.getCharacterByIdDistinctUntilChanged(characterId.toInt()).map {
                    it?.toCharacterDetailModel()
                }

            override suspend fun fetchFromRemote() = charactersService.getCharacterById(characterId)

            override fun shouldFetch(data: CharacterDetailModel?) = (data == null)

        }.asFlow().flowOn(Dispatchers.IO)

}
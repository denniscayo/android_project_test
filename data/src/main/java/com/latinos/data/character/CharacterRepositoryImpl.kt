package com.latinos.data.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.latinos.data.character.paging.CharacterPagingSource
import com.latinos.domain.characters.repository.CharacterRepository
import com.latinos.services.remote.charaters.CharactersService
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val charactersService: CharactersService,
) : CharacterRepository {
    override fun getPaginatedCharacters() = Pager(
        PagingConfig(pageSize = 50)
    ) {
        CharacterPagingSource(charactersService)
    }.flow

}
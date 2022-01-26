package com.latinos.domain.characters.usecase

import androidx.paging.PagingData
import com.latinos.domain.characters.model.CharacterModel
import com.latinos.domain.characters.repository.CharacterRepository
import com.latinos.domain.utils.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPaginatedCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) : FlowUseCase<Unit, Flow<PagingData<CharacterModel>>> {
    override fun execute(params: Unit) =
        characterRepository.getPaginatedCharacters().map { pagingData ->
            pagingData
        }
}
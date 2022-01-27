package com.latinos.domain.characters.usecase

import com.latinos.domain.characters.model.CharacterDetailModel
import com.latinos.domain.characters.repository.CharacterRepository
import com.latinos.domain.utils.repository.Result
import com.latinos.domain.utils.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) : UseCase<GetCharacterByIdUseCase.Params, Flow<@JvmSuppressWildcards Result<CharacterDetailModel?>>> {

    data class Params(val characterId: String)

    override fun execute(params: Params): Flow<Result<CharacterDetailModel?>> {
        return characterRepository.getCharacterById(params.characterId).mapNotNull { response ->

            val result = response.data

            return@mapNotNull when (response) {
                is Result.Success -> Result.Success(result)
                is Result.Error -> Result.Error(response.error, result)
            }
        }
    }


}
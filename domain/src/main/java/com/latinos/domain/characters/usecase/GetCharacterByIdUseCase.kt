package com.latinos.domain.characters.usecase

import com.latinos.common.utils.dispatchers.DispatcherProvider
import com.latinos.common.utils.either.Either
import com.latinos.data.utils.FlowUseCase
import com.latinos.domain.characters.model.CharacterDetailModel
import com.latinos.domain.characters.model.CharacterErrorModel
import com.latinos.domain.characters.repository.CharacterRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    dispatcherProvider: DispatcherProvider,
) :
    FlowUseCase<String, Either<CharacterDetailModel, CharacterErrorModel>>(dispatcherProvider) {

    override fun prepareFlow(input: String) = flow {
        emit(characterRepository.getCharacterById(input))
    }
}
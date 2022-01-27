package com.latinos.domain.characters.usecase

import com.latinos.domain.characters.model.CharacterDetailModel
import com.latinos.domain.characters.repository.CharacterRepository
import com.latinos.domain.utils.dispatchers.DispatcherProvider
import com.latinos.domain.utils.usecase.FlowUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    dispatcherProvider: DispatcherProvider,
) : FlowUseCase<String, CharacterDetailModel>(dispatcherProvider) {

    override fun prepareFlow(input: String) =
        flow { emit(characterRepository.getCharacterById(input)) }


}
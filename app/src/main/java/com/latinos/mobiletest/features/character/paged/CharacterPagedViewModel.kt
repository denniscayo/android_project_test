package com.latinos.mobiletest.features.character.paged

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latinos.domain.characters.usecase.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterPagedViewModel @Inject constructor(
    GetCharacterByIdUseCase: GetCharacterByIdUseCase,
) :
    ViewModel() {

    val moviesPaged = getPaginatedCharactersUseCase.execute(Unit).cachedIn(viewModelScope)
}
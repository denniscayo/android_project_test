package com.latinos.mobiletest.features.character.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.latinos.domain.characters.usecase.GetPaginatedCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    getPaginatedCharactersUseCase: GetPaginatedCharactersUseCase,
) :
    ViewModel() {

    val moviesPaged = getPaginatedCharactersUseCase.execute(Unit).cachedIn(viewModelScope)
}
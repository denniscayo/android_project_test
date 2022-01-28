package com.latinos.mobiletest.features.character.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latinos.common.utils.either.onFailure
import com.latinos.common.utils.either.onSuccess
import com.latinos.domain.characters.model.CharacterDetailModel
import com.latinos.domain.characters.model.CharacterErrorModel
import com.latinos.domain.characters.usecase.GetCharacterByIdUseCase
import com.latinos.domain.utils.error.GlobalErrorMapper
import com.latinos.domain.utils.error.GlobalErrorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val errorMapper: GlobalErrorMapper,
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Idle)
    val state: StateFlow<State> = _state

    private val _events = Channel<Event>(Channel.RENDEZVOUS)
    val events = _events.receiveAsFlow()

    private var _characterDetailModel = MutableStateFlow(CharacterDetailModel())
    val characterDetailModel: StateFlow<CharacterDetailModel> = _characterDetailModel

    fun getCharacterByIdUseCase(characterId: String) =
        getCharacterByIdUseCase
            .prepare(characterId)
            .onEach {
                it.onSuccess { model ->
                    _characterDetailModel.value = model
                }
                it.onFailure { error -> _events.send(Event.CharacterError(error)) }
            }
            .catch { _events.send(Event.Error(errorMapper.map(it))) }
            .launchIn(viewModelScope)

    sealed class State {
        object Idle : State()
        data class Loading(val showLoading: Boolean = true) : State()
    }

    sealed class Event {
        data class ErrorCharacter(val text: Int) : Event()
        data class Error(val type: GlobalErrorType = GlobalErrorType.GENERIC_ERROR) : Event()
        data class CharacterError(val error: CharacterErrorModel) : Event()
    }
}
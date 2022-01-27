package com.latinos.mobiletest.features.character.detail

import androidx.lifecycle.ViewModel
import com.latinos.domain.characters.model.CharacterDetailModel
import com.latinos.domain.characters.usecase.GetCharacterByIdUseCase
import com.latinos.domain.utils.repository.Result
import com.latinos.mobiletest.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Idle)
    val state: StateFlow<State> = _state

    private val _events = Channel<Event>(Channel.RENDEZVOUS)
    val events = _events.receiveAsFlow()

    private var _characterDetailModel = MutableStateFlow(CharacterDetailModel())
    val workoutDetailModel: StateFlow<CharacterDetailModel> = _characterDetailModel

    fun getCharacterByIdUseCase(characterId: String) {
        getCharacterByIdUseCase.execute(
            GetCharacterByIdUseCase.Params(characterId)
        ).onEach { result ->

            result.data?.let {
                _characterDetailModel.value = result.data!!
            }
            determineErrorMessage(result.error)
        }
    }

    private suspend fun determineErrorMessage(error: Result.ErrorType?) = when (error) {
        is Result.ErrorType.DatabaseError -> _events.send(Event.ErrorCharacter(R.string.error_database))
        is Result.ErrorType.HttpError -> _events.send(Event.ErrorCharacter(R.string.error_network))
        is Result.ErrorType.IOError -> _events.send(Event.ErrorCharacter(R.string.error_connection))
        is Result.ErrorType.Generic -> _events.send(Event.ErrorCharacter(R.string.error_generic))
        null -> null
    }

    sealed class State {
        object Idle : State()
        data class Loading(val showLoading: Boolean = true) : State()
    }

    sealed class Event {
        data class ErrorCharacter(val text: Int) : Event()
        object NavigateToUnregisteredLogin : Event()
    }
}
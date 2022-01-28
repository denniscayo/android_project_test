package com.latinos.mobiletest.features.character

import app.cash.turbine.test
import com.latinos.common.utils.either.eitherFailure
import com.latinos.common.utils.either.eitherSuccess
import com.latinos.common.utils.testing.MainCoroutinesRule
import com.latinos.domain.characters.model.CharacterErrorModel
import com.latinos.domain.characters.usecase.GetCharacterByIdUseCase
import com.latinos.domain.utils.error.GlobalErrorMapper
import com.latinos.mobiletest.features.base.state.StateView
import com.latinos.mobiletest.features.character.detail.CharacterDetailViewModel
import com.latinos.mobiletest.tools.ModelsMocked.characterDetailModelMocked
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CharacterDetailViewModelTest {
    @get:Rule
    val coroutineTestRule = MainCoroutinesRule()

    private var getCharacterByIdUseCase = mockk<GetCharacterByIdUseCase>()
    private var globalErrorMapper = mockk<GlobalErrorMapper>()

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    @Before
    fun setUp() {
        characterDetailViewModel =
            CharacterDetailViewModel(getCharacterByIdUseCase, globalErrorMapper)
    }

    @Test
    fun `getCharacterById returns state idle when characterId empty and service is not called`() {
        val characterId = ""

        characterDetailViewModel.getCharacterById(characterId)

        assertEquals(characterDetailViewModel.state.value, StateView.Idle)

        verify(exactly = 0) { getCharacterByIdUseCase.prepare(characterId) }
        confirmVerified()
    }

    @Test
    fun `with characterId not empty and invalid id, getCharacterByID service is called but return CharacterNotExits`() =
        coroutineTestRule.runTest {
            val characterId = "1000000"
            val characterNotExitsError = CharacterErrorModel.CharacterNotExits

            every { getCharacterByIdUseCase.prepare(characterId) }.returns(flow {
                emit(eitherFailure(characterNotExitsError))
            })

            characterDetailViewModel.getCharacterById(characterId)
            characterDetailViewModel.events.test {
                characterDetailViewModel.getCharacterById(characterId)
                assertEquals(CharacterDetailViewModel.Event.CharacterError(characterNotExitsError),
                    awaitItem())
                cancelAndConsumeRemainingEvents()
            }

            confirmVerified()
        }

    @Test
    fun `with characterId not empty and valid id, getCharacterByID service is called`() =
        coroutineTestRule.runTest {
            val characterId = "123456"

            every { getCharacterByIdUseCase.prepare(characterId) }.returns(flow {
                emit(eitherSuccess(characterDetailModelMocked()))
            })

            characterDetailViewModel.getCharacterById(characterId)

            verify(exactly = 1) { getCharacterByIdUseCase.prepare(characterId) }
            confirmVerified()
        }
}
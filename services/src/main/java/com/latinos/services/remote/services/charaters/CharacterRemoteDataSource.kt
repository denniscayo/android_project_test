package com.latinos.services.remote.services.charaters

import com.latinos.common.utils.either.Either
import com.latinos.common.utils.either.map
import com.latinos.services.remote.manager.error.BSANNetworkException
import com.latinos.services.remote.manager.error.BSANServiceException
import com.latinos.services.remote.services.RestServices
import com.latinos.services.remote.services.charaters.dto.CharacterDTO
import com.latinos.services.remote.services.charaters.error.CharacterErrorDTO
import com.latinos.services.remote.services.charaters.error.CharacterErrorMapper
import javax.inject.Inject
import javax.inject.Singleton

interface CharacterRemoteDataSource {
    @Throws(BSANNetworkException::class, BSANServiceException::class)
    fun getCharacterById(characterId: String): Either<CharacterDTO, CharacterErrorDTO>
}

@Singleton
internal class CharacterRemoteDataSourceImpl
@Inject
constructor(
    private val restServices: RestServices,
    private val charactersService: CharactersService,
    private val errorMapper: CharacterErrorMapper,
) : CharacterRemoteDataSource {
    override fun getCharacterById(characterId: String): Either<CharacterDTO, CharacterErrorDTO> {
        return restServices.execute(charactersService.getCharacterById(characterId))
            .map(onSuccess = { it.data }, onFailure = { errorMapper.mapLoginError(it) })
    }
}
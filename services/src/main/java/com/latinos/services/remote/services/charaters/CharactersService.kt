package com.latinos.services.remote.services.charaters

import com.latinos.services.remote.services.charaters.dto.CharacterDTO
import com.latinos.services.remote.services.charaters.dto.CharactersDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {

    @GET("/v1/public/characters")
    suspend fun getPagedCharacters(
        @Query("offset") offset: String,
        @Query("limit") limit: Int,
    ): Response<CharactersDTO>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacterById(
        @Path("characterId") characterId: String,
    ): Call<CharacterDTO>
}
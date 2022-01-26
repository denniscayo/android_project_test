package com.latinos.services.remote.charaters

import com.latinos.services.remote.charaters.dto.CharacterDTO
import com.latinos.services.remote.charaters.dto.CharactersDTO
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
    suspend fun getCharacterById(
        @Path("characterId") characterId: String,
        @Query("ts") ts: String = CharactersServiceData.timeStamp,
        @Query("apikey") apikey: String = CharactersServiceData.API_KEY,
        @Query("hash") hash: String = CharactersServiceData.hash(),
    ): Call<CharacterDTO>
}
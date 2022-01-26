package com.latinos.services.remote.charaters

import com.latinos.services.remote.charaters.dto.CharacterDTO
import com.latinos.services.remote.charaters.dto.CharactersDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharatersService {

    //TODO: refactor a un interceptor¿?
    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey") apikey: String = CharatersServiceData.API_KEY,
        @Query("ts") ts: String = CharatersServiceData.timeStamp,
        @Query("hash") hash: String = CharatersServiceData.hash(),
        @Query("offset") offset: String,
    ): CharactersDTO

    //TODO: refactor a un interceptor¿?
    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: String,
        @Query("ts") ts: String = CharatersServiceData.timeStamp,
        @Query("apikey") apikey: String = CharatersServiceData.API_KEY,
        @Query("hash") hash: String = CharatersServiceData.hash(),
    ): CharacterDTO
}
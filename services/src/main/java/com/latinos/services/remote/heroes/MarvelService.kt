package com.latinos.services.remote.heroes

import com.latinos.services.remote.heroes.dto.CharacterDTO
import com.latinos.services.remote.heroes.dto.CharactersDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    //TODO: refactor a un interceptor¿?
    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey") apikey: String = MarvelServiceData.API_KEY,
        @Query("ts") ts: String = MarvelServiceData.timeStamp,
        @Query("hash") hash: String = MarvelServiceData.hash(),
        @Query("offset") offset: String,
    ): CharactersDTO

    //TODO: refactor a un interceptor¿?
    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: String,
        @Query("ts") ts: String = MarvelServiceData.timeStamp,
        @Query("apikey") apikey: String = MarvelServiceData.API_KEY,
        @Query("hash") hash: String = MarvelServiceData.hash(),
    ): CharacterDTO
}
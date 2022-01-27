package com.latinos.services.remote.services.charaters.error

import com.google.gson.Gson
import com.latinos.services.remote.services.RestError
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CharacterErrorMapper @Inject constructor(private val gson: Gson) {
    fun mapLoginError(error: RestError) =
        try {
            val errorResponse = gson.fromJson(error.description, CharacterErrorResponse::class.java)
            when (error.statusCode) {
                else -> CharacterErrorDTO.Generic
            }
        } catch (t: Throwable) {
            CharacterErrorDTO.Generic
        }
}
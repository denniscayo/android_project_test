package com.latinos.services.remote.heroes.dto

data class CharacterDTO(
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    val data: DataDTO,
    val etag: String,
    val status: String,
)
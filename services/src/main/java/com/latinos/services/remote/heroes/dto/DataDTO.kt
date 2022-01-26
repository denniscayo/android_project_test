package com.latinos.services.remote.heroes.dto

data class DataDTO(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<ResultDTO>,
    val total: String,
)
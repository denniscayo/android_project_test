package com.latinos.services.remote.services.charaters.dto

data class
DataDTO(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultDTO>,
    val total: Int,
)
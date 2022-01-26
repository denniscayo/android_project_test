package com.latinos.services.remote.charaters.dto

data class StoriesDTO(
    val available: String,
    val collectionURI: String,
    val items: List<ItemDTO>,
    val returned: String,
)
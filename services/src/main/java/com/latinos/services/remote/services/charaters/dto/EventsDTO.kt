package com.latinos.services.remote.services.charaters.dto

data class EventsDTO(
    val available: String,
    val collectionURI: String,
    val items: List<ItemDTO>,
    val returned: String,
)
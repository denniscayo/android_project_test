package com.latinos.services.remote.heroes.dto

data class ResultDTO(
    val comics: ComicsDTO,
    val description: String,
    val events: EventsDTO,
    val id: String,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: SeriesDTO,
    val stories: StoriesDTO,
    val thumbnail: ThumbnailDTO,
    val urls: List<UrlDTO>,
)
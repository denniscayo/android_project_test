package com.latinos.data.character.mapper

import com.latinos.database.entity.CharacterEntity
import com.latinos.domain.characters.model.CharacterDetailModel
import com.latinos.domain.characters.model.CharacterErrorModel
import com.latinos.domain.characters.model.CharacterModel
import com.latinos.services.remote.services.charaters.dto.CharacterDTO
import com.latinos.services.remote.services.charaters.dto.ResultDTO
import com.latinos.services.remote.services.charaters.dto.ThumbnailDTO
import com.latinos.services.remote.services.charaters.error.CharacterErrorDTO

fun List<ResultDTO>.toCharacterModelList() = map { it.toCharacterModel() }

fun ResultDTO.toCharacterModel() = CharacterModel(id, name, description, thumbnail.toImage())

fun CharacterEntity.toCharacterDetailModel() =
    CharacterDetailModel(id.toString(),
        name,
        description,
        image,
        dataSeries,
        dataComics,
        dataEvents,
        attributionText)

fun ThumbnailDTO.toImage() = "$path.$extension"

fun CharacterDTO.toCharacterDetailModel(): CharacterDetailModel {
    val character = data.results[0]

    return CharacterDetailModel(character.id,
        character.name,
        character.description,
        character.thumbnail.toImage(),
        character.comics.available,
        character.series.available,
        character.events.available,
        attributionText)
}

fun CharacterDTO.toCharacterEntity(): CharacterEntity {
    val character = data.results[0]

    return CharacterEntity(character.id.toInt(),
        character.name,
        character.description,
        character.thumbnail.toImage(),
        character.comics.available,
        character.series.available,
        character.events.available,
        attributionText)
}

fun CharacterErrorDTO.toCharacterErrorModel() = CharacterErrorModel.Generic
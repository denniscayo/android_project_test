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
    CharacterDetailModel(id.toString(), name, description, image)

fun ResultDTO.toCharacterEntity() =
    CharacterEntity(id.toInt(), name, description, thumbnail.toImage())

fun ThumbnailDTO.toImage() = "$path.$extension"

fun CharacterDTO.toCharacterDetailModel() = CharacterDetailModel(data.results[0].id,
    data.results[0].name,
    data.results[0].description,
    data.results[0].thumbnail.toImage())

fun CharacterDTO.toCharacterEntity() = CharacterEntity(data.results[0].id.toInt(),
    data.results[0].name,
    data.results[0].description,
    data.results[0].thumbnail.toImage())

fun CharacterErrorDTO.toCharacterErrorModel() = CharacterErrorModel.Generic
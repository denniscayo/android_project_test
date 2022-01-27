package com.latinos.data.character.mapper

import com.latinos.database.entity.CharacterEntity
import com.latinos.domain.characters.model.CharacterDetailModel
import com.latinos.domain.characters.model.CharacterModel
import com.latinos.services.remote.charaters.dto.ResultDTO
import com.latinos.services.remote.charaters.dto.ThumbnailDTO

fun List<ResultDTO>.toCharacterModelList() = map { it.toCharacterModel() }

fun ResultDTO.toCharacterModel() = CharacterModel(id, name, description, thumbnail.toImage())

fun CharacterEntity.toCharacterDetailModel() =
    CharacterDetailModel(id.toString(), name, description, image)

fun ResultDTO.toCharacterEntity() =
    CharacterEntity(id.toInt(), name, description, thumbnail.toImage())


fun ThumbnailDTO.toImage() = "$path.$extension"
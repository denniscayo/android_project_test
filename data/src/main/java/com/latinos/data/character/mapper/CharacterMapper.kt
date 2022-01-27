package com.latinos.data.character.mapper

import com.latinos.domain.characters.model.CharacterModel
import com.latinos.services.remote.charaters.dto.ResultDTO
import com.latinos.services.remote.charaters.dto.ThumbnailDTO

fun List<ResultDTO>.toCharacterModelList() = map { it.toCharacterModel() }

fun ResultDTO.toCharacterModel() = CharacterModel(id, name, description, thumbnail.toImage())

fun ThumbnailDTO.toImage() = "$path.$extension"
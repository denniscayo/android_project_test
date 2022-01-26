package com.latinos.data.character.mapper

import com.latinos.domain.characters.model.CharacterModel
import com.latinos.services.remote.charaters.dto.ResultDTO

fun List<ResultDTO>.toCharacterModelList() = map { it.toCharacterModel() }

fun ResultDTO.toCharacterModel() = CharacterModel(id, name)
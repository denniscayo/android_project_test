package com.latinos.domain.characters.model

/*
* Here We will manage all the errors from server.
* From now we use generic
* */
sealed class CharacterErrorModel {
    object Generic : CharacterErrorModel()
    object CharacterNotExits : CharacterErrorModel()
}
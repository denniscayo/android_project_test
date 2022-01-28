package com.latinos.mobiletest.tools

import com.appmattus.kotlinfixture.kotlinFixture
import com.latinos.domain.characters.model.CharacterDetailModel

object ModelsMocked {
    val fixture = kotlinFixture()

    fun characterDetailModelMocked() = fixture<CharacterDetailModel>()
}
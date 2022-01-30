package com.latinos.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.latinos.database.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterEntity(characterEntity: CharacterEntity)

    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    fun getCharacterById(id: Int): CharacterEntity?
}
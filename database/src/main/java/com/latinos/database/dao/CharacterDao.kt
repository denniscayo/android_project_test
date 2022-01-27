package com.latinos.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.latinos.database.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterEntity(characterEntity: CharacterEntity)

    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    fun getCharacterById(id: Int): Flow<CharacterEntity?>

    fun getCharacterByIdDistinctUntilChanged(id: Int) =
        getCharacterById(id).distinctUntilChanged()
}
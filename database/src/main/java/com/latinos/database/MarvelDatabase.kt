package com.latinos.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.latinos.database.dao.CharacterDao
import com.latinos.database.entity.CharacterEntity

@Database(
    entities = [
        CharacterEntity::class
    ], version = 1
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
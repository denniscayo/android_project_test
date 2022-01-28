package com.latinos.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "data_series") val dataSeries: String,
    @ColumnInfo(name = "data_comics") val dataComics: String,
    @ColumnInfo(name = "data_events") val dataEvents: String,
    @ColumnInfo(name = "attribution_text") val attributionText: String,
) {
}
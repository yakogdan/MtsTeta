package com.yakogdan.data.database.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yakogdan.data.database.room.entities.MovieGenreDb.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class MovieGenreDb(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String = ""
) {
    companion object {
        const val TABLE_NAME = "genres_table"
    }
}
package com.yakogdan.data.database.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yakogdan.data.database.room.entities.MovieCardDbEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MovieCardDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "rate_scope")
    val rateScore: Double = 0.0,
    @ColumnInfo(name = "age_restriction")
    val ageRestriction: Int = 0,
    @ColumnInfo(name = "poster_url")
    val posterUrl: String = "",
) {
    companion object {
        const val TABLE_NAME = "movies_table"
    }
}
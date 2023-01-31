package com.yakogdan.data.database.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yakogdan.data.database.room.entities.MovieCardDb.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MovieCardDb(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "description")
    val description: String = "",

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = 0.0,

    @ColumnInfo(name = "age_restriction")
    val ageRestriction: Int = 0,

    @ColumnInfo(name = "adult", defaultValue = "true")
    val adult: Boolean = true,

    @ColumnInfo(name = "poster_path")
    val posterPath: String = "",
) {
    companion object {
        const val TABLE_NAME = "movies_table"
    }
}
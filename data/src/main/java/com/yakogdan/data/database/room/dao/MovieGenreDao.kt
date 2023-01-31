package com.yakogdan.data.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yakogdan.data.database.room.entities.MovieGenreDb
import com.yakogdan.data.database.room.entities.MovieGenreDb.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieGenreDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getMovieGenres(): Flow<List<MovieGenreDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieGenres(movieGenres: List<MovieGenreDb>)

    @Query("SELECT (SELECT COUNT(*) FROM $TABLE_NAME) == 0")
    suspend fun isEmpty(): Boolean
}
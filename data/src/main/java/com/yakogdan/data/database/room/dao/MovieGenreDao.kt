package com.yakogdan.data.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yakogdan.data.database.room.entities.MovieGenreDbEntity
import com.yakogdan.data.database.room.entities.MovieGenreDbEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieGenreDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getMovieGenres(): Flow<List<MovieGenreDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieGenres(movieGenres: List<MovieGenreDbEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieGenre(movieGenre: MovieGenreDbEntity)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clearAllDb()
}
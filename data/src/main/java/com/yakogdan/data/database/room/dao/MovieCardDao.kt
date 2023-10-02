package com.yakogdan.data.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yakogdan.data.database.room.entities.MovieCardDb
import com.yakogdan.data.database.room.entities.MovieCardDb.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCardDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getMovieCards(): Flow<List<MovieCardDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieCard(movieCard: MovieCardDb)

    @Delete
    suspend fun deleteMovieCard(movieCard: MovieCardDb)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clearAllDB()

    @Query("SELECT (SELECT COUNT(*) FROM $TABLE_NAME) == 0")
    suspend fun isEmpty(): Boolean
}
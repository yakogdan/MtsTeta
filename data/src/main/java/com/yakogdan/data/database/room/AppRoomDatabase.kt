package com.yakogdan.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yakogdan.data.database.room.dao.MovieCardDao
import com.yakogdan.data.database.room.dao.MovieGenreDao
import com.yakogdan.data.database.room.entities.MovieCardDbEntity
import com.yakogdan.data.database.room.entities.MovieGenreDbEntity

@Database(
    entities = [
        MovieCardDbEntity::class,
        MovieGenreDbEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun movieCardDao(): MovieCardDao
    abstract fun movieGenreDao(): MovieGenreDao

    companion object {

        private const val DATABASE_NAME = "movies_database"

        fun create(context: Context): AppRoomDatabase =
            Room.databaseBuilder(
                context,
                AppRoomDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}
package com.yakogdan.data.database.room

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yakogdan.data.database.room.dao.MovieCardDao
import com.yakogdan.data.database.room.dao.MovieGenreDao
import com.yakogdan.data.database.room.entities.MovieCardDb
import com.yakogdan.data.database.room.entities.MovieGenreDb

@Database(
    entities = [
        MovieCardDb::class,
        MovieGenreDb::class
    ],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
            spec = AutoMigrationSpec1To2::class
        )
    ]
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
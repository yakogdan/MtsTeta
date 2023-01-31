package com.yakogdan.data.database.room

import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf
import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase

@RenameColumn.Entries(
    value = [
        RenameColumn(
            tableName = "movies_table",
            fromColumnName = "rate_scope",
            toColumnName = "vote_average"
        ),
        RenameColumn(
            tableName = "movies_table",
            fromColumnName = "poster_url",
            toColumnName = "poster_path"
        )
    ]
)
class AutoMigrationSpec1To2 : AutoMigrationSpec {
    override fun onPostMigrate(db: SupportSQLiteDatabase) {
        super.onPostMigrate(db)
        db.query("SELECT * FROM movies_table").use { cursor ->
            val idIndex = cursor.getColumnIndex("id")
            val ageRestrictionIndex = cursor.getColumnIndex("age_restriction")
            val posterPathIndex = cursor.getColumnIndex("poster_path")
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idIndex)
                val ageRestriction = cursor.getInt(ageRestrictionIndex)
                val adult = ageRestriction > 12
                val posterUrl = cursor.getString(posterPathIndex)
                val urlList = posterUrl.split("/")
                val posterPath = urlList[urlList.size-1]
                db.update(
                    "movies_table",
                    SQLiteDatabase.CONFLICT_NONE,
                    contentValuesOf(
                        "adult" to adult,
                        "poster_path" to posterPath
                    ),
                    "id = ?",
                    arrayOf(id.toString())
                )
            }
        }
    }
}
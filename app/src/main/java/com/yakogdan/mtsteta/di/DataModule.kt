package com.yakogdan.mtsteta.di

import android.content.Context
import com.yakogdan.data.database.room.AppRoomDatabase
import com.yakogdan.data.database.room.dao.MovieCardDao
import com.yakogdan.data.database.room.dao.MovieGenreDao
import com.yakogdan.data.repositories.MovieListRepoImpl
import com.yakogdan.domain.repositories.MovieListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideMovieListRepository(
        movieCardDao: MovieCardDao,
        movieGenreDao: MovieGenreDao
    ): MovieListRepository {
        return MovieListRepoImpl(movieCardDao, movieGenreDao)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppRoomDatabase {
        return AppRoomDatabase.create(context)
    }

    @Provides
    fun provideMovieCardDao(appRoomDatabase: AppRoomDatabase): MovieCardDao {
        return appRoomDatabase.movieCardDao()
    }

    @Provides
    fun provideMovieGenreDao(appRoomDatabase: AppRoomDatabase): MovieGenreDao {
        return appRoomDatabase.movieGenreDao()
    }
}
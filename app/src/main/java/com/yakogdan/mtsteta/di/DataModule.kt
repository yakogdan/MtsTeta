package com.yakogdan.mtsteta.di

import android.content.Context
import com.yakogdan.data.database.room.AppRoomDatabase
import com.yakogdan.data.database.room.dao.MovieCardDao
import com.yakogdan.data.database.room.dao.MovieGenreDao
import com.yakogdan.data.remote.api.TheMovieDbApi
import com.yakogdan.data.repositories.MovieRepoImpl
import com.yakogdan.domain.repositories.MovieRepository
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
        movieGenreDao: MovieGenreDao,
        api: TheMovieDbApi

    ): MovieRepository {
        return MovieRepoImpl(movieCardDao, movieGenreDao, api)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppRoomDatabase {
        return AppRoomDatabase.create(context)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): TheMovieDbApi {
        return TheMovieDbApi.create()
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
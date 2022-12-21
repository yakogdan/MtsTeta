package com.yakogdan.mtsteta.di

import com.yakogdan.data.repositories.MovieListRepoImpl
import com.yakogdan.domain.repositories.MovieListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideMovieListRepository(): MovieListRepository {
        return MovieListRepoImpl()
    }
}
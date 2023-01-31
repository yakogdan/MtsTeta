package com.yakogdan.mtsteta.di

import com.yakogdan.domain.interactors.MovieListInteractor
import com.yakogdan.domain.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideNoteInteractor(movieRepository: MovieRepository): MovieListInteractor {
        return MovieListInteractor(movieRepository)
    }
}
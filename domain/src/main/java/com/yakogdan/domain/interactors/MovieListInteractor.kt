package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.MovieCardDomainEntity
import com.yakogdan.domain.entities.MovieGenresDomainEntity
import com.yakogdan.domain.repositories.MovieListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListInteractor @Inject constructor(
    private val movieListRepository: MovieListRepository
) {

    suspend fun getMovieCards(): Flow<List<MovieCardDomainEntity>> =
        movieListRepository.getMovieCards()

    suspend fun getMovieGenres(): Flow<List<MovieGenresDomainEntity>> =
        movieListRepository.getMovieGenres()
}